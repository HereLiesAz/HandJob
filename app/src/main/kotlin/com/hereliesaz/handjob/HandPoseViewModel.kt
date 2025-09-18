package com.hereliesaz.handjob

import androidx.lifecycle.ViewModel
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HandPoseViewModel : ViewModel() {

    private val _handLandmarkerResult = MutableStateFlow<HandLandmarkerResult?>(null)
    val handLandmarkerResult = _handLandmarkerResult.asStateFlow()

    // TODO: Re-integrate SceneView state management when the 3D rendering is added back.
    // This includes state for model rotation, lighting, etc.

    fun onHandResults(result: HandLandmarkerResult?) {
        _handLandmarkerResult.value = result
    }
}
