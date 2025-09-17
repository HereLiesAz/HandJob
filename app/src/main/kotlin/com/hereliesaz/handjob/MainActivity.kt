package com.example.handmannequin

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
import com.example.handmannequin.ui.theme.HandMannequinTheme
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
    val modelRotationX by viewModel.modelRotationX.collectAsState()
    val modelRotationY by viewModel.modelRotationY.collectAsState()
    val modelRotationZ by viewModel.modelRotationZ.collectAsState()
    val lightRotation by viewModel.lightRotation.collectAsState()
    val lightIntensity by viewModel.lightIntensity.collectAsState()
    val lightDiffusion by viewModel.lightDiffusion.collectAsState()
    val ambientBrightness by viewModel.ambientBrightness.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        SceneViewer(
            modifier = Modifier.fillMaxSize(),
            handResult = handResult,
            modelRotationX = modelRotationX,
            modelRotationY = modelRotationY,
            modelRotationZ = modelRotationZ,
            lightRotation = lightRotation,
            lightIntensity = lightIntensity,
            lightDiffusion = lightDiffusion,
            ambientBrightness = ambientBrightness
        )

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

        // Controls
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ControlSlider(label = "Model Rot X", value = modelRotationX, onValueChange = viewModel::onModelRotationXChange, range = 0f..360f)
            ControlSlider(label = "Model Rot Y", value = modelRotationY, onValueChange = viewModel::onModelRotationYChange, range = 0f..360f)
            ControlSlider(label = "Model Rot Z", value = modelRotationZ, onValueChange = viewModel::onModelRotationZChange, range = 0f..360f)
            ControlSlider(label = "Light Angle", value = lightRotation, onValueChange = viewModel::onLightRotationChange, range = 0f..360f)
            ControlSlider(label = "Light Power", value = lightIntensity, onValueChange = viewModel::onLightIntensityChange, range = 10_000f..200_000f)
            ControlSlider(label = "Light Diffusion", value = lightDiffusion, onValueChange = viewModel::onLightDiffusionChange, range = 0f..1f)
            ControlSlider(label = "Ambient", value = ambientBrightness, onValueChange = viewModel::onAmbientBrightnessChange, range = 0f..1f)
        }
    }
}

@Composable
private fun ControlSlider(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    range: ClosedFloatingPointRange<Float>
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = Color.White,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(80.dp)
        )
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = range,
            modifier = Modifier.weight(1f)
        )
    }
}
