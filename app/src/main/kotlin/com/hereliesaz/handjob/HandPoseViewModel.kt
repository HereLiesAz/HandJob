package com.hereliesaz.handjob

import androidx.lifecycle.ViewModel
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HandPoseViewModel : ViewModel() {

    private val _handLandmarkerResult = MutableStateFlow<HandLandmarkerResult?>(null)
    val handLandmarkerResult = _handLandmarkerResult.asStateFlow()

    fun onHandResults(result: HandLandmarkerResult?) {
        _handLandmarkerResult.value = result
    }
}
