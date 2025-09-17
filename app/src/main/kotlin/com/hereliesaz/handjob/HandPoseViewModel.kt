package com.example.handmannequin

import androidx.lifecycle.ViewModel
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HandPoseViewModel : ViewModel() {

    private val _handLandmarkerResult = MutableStateFlow<HandLandmarkerResult?>(null)
    val handLandmarkerResult = _handLandmarkerResult.asStateFlow()

    private val _modelRotationX = MutableStateFlow(0f)
    val modelRotationX = _modelRotationX.asStateFlow()

    private val _modelRotationY = MutableStateFlow(0f)
    val modelRotationY = _modelRotationY.asStateFlow()
    
    private val _modelRotationZ = MutableStateFlow(0f)
    val modelRotationZ = _modelRotationZ.asStateFlow()

    private val _lightRotation = MutableStateFlow(45f) // Angle in degrees
    val lightRotation = _lightRotation.asStateFlow()

    private val _lightIntensity = MutableStateFlow(100_000f)
    val lightIntensity = _lightIntensity.asStateFlow()

    private val _lightDiffusion = MutableStateFlow(0.5f) // 0 (hard) to 1 (soft)
    val lightDiffusion = _lightDiffusion.asStateFlow()
    
    private val _ambientBrightness = MutableStateFlow(0.2f)
    val ambientBrightness = _ambientBrightness.asStateFlow()

    fun onHandResults(result: HandLandmarkerResult?) {
        _handLandmarkerResult.value = result
    }

    fun onModelRotationXChange(value: Float) {
        _modelRotationX.value = value
    }

    fun onModelRotationYChange(value: Float) {
        _modelRotationY.value = value
    }

    fun onModelRotationZChange(value: Float) {
        _modelRotationZ.value = value
    }

    fun onLightRotationChange(value: Float) {
        _lightRotation.value = value
    }

    fun onLightIntensityChange(value: Float) {
        _lightIntensity.value = value
    }

    fun onLightDiffusionChange(value: Float) {
        _lightDiffusion.value = value
    }
    
    fun onAmbientBrightnessChange(value: Float) {
        _ambientBrightness.value = value
    }
}
