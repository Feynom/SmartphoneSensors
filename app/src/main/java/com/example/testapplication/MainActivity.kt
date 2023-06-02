package com.example.testapplication

import AccelerometerManager
import GForceMeterManager
import GyroscopeManager
import LightSensorManager
import MagnetometerManager
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var gyroscopeValueTextView: TextView
    private lateinit var accelerometerValueTextView: TextView
    private lateinit var magnetometerValueTextView: TextView
    private lateinit var soundValueTextView: TextView
    private lateinit var lightValueTextView: TextView
    private lateinit var gForceValueTextView: TextView

    private lateinit var gyroscopeManager: GyroscopeManager
    private lateinit var accelerometerManager: AccelerometerManager
    private lateinit var magnetometerManager: MagnetometerManager
    private lateinit var lightSensorManager: LightSensorManager
    private lateinit var gForceMeterManager: GForceMeterManager

    private val updateIntervalMillis: Long = 1000 / 16

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViews()

        initSensors()

        startMainLoop()
    }

    private fun findViews() {
        gyroscopeValueTextView = findViewById(R.id.tvGyroscopeSensorValueText)
        accelerometerValueTextView = findViewById(R.id.tvAccelerometerSensorValueText)
        magnetometerValueTextView = findViewById(R.id.tvMagnetometerSensorValueText)
        lightValueTextView = findViewById(R.id.tvLightSensorValueText)
        gForceValueTextView = findViewById(R.id.tvGForceSensorValueText)
    }

    private fun initSensors() {
        gyroscopeManager = GyroscopeManager(this);
        accelerometerManager = AccelerometerManager(this)
        magnetometerManager = MagnetometerManager(this)
        lightSensorManager = LightSensorManager(this)
        gForceMeterManager = GForceMeterManager(this)
    }

    private fun startMainLoop() {
        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                updateValues()
                handler.postDelayed(this, updateIntervalMillis)
            }
        }

        runnable.run()
    }

    private fun updateValues() {
        updateGyroscopeValue()
        updateAccelerometerValue()
        updateMagnetometerValue()
        updateLightValue()
        updateGForceValue()
    }

    private fun updateGyroscopeValue() {
        gyroscopeValueTextView.text = getGyroscopeValue().toString()
    }

    private fun updateAccelerometerValue() {
        accelerometerValueTextView.text = getAccelerometerValue().toString()
    }

    private fun updateMagnetometerValue() {
        magnetometerValueTextView.text = getMagnetometerValue().toString()
    }

    private fun updateLightValue() {
        lightValueTextView.text = getLightValue().toString() + " lux"
    }

    private fun updateGForceValue() {
        gForceValueTextView.text = getGForceValue().toString()
    }

    private fun getGyroscopeValue(): Vector3 {
        return gyroscopeManager.getGyroscopeValues() ?: Vector3(0f, 0f, 0f)
    }

    private fun getAccelerometerValue(): Vector3 {
        return accelerometerManager.getAccelerometerValues() ?: Vector3(0f, 0f, 0f)
    }

    private fun getMagnetometerValue(): Vector3 {
        return magnetometerManager.getMagnetometerValues() ?: Vector3(0f, 0f, 0f)
    }

    private fun getLightValue(): Float {
        return lightSensorManager.getLightValue() ?: 0f
    }

    private fun getGForceValue(): Vector3 {
        return gForceMeterManager.getGForceValues() ?: Vector3(0f, 0f, 0f)
    }
}
