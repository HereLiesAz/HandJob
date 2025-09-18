package com.hereliesaz.handjob

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hereliesaz.handjob.ui.theme.HandMannequinTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

class MainActivity : ComponentActivity() {

    private val viewModel: HandPoseViewModel by viewModels()

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HandMannequinTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

                    if (cameraPermissionState.status.isGranted) {
                        HandMannequinApp(viewModel)
                    } else {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Camera permission is required.")
                            Spacer(modifier = Modifier.height(8.dp))
                            androidx.compose.material3.Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                                Text("Grant Permission")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HandMannequinApp(viewModel: HandPoseViewModel) {
    val handResult by viewModel.handLandmarkerResult.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        // Camera preview in a corner
        CameraView(
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(width = 120.dp, height = 160.dp)
                .padding(8.dp),
            onResults = { result ->
                viewModel.onHandResults(result)
            }
        )

        // Placeholder for the 3D view
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("3D View Placeholder")
        }
    }
}
