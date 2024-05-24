package com.walktalk.stride

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlin.properties.Delegates

class ExerciseService : Service(), SensorEventListener {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var sensorManager: SensorManager
    private var stepSensor: Sensor? = null
    private var step: Int = 0
    private var latLng: LatLng = LatLng(0.0, 0.0)
    private lateinit var handler: Handler
    private val delayMillis: Long = 20000
    private val firstMillis: Long = 100
    private var isStart: Boolean by Delegates.observable(false) { _, oldValue, newValue ->
        if (!oldValue && newValue) {
            handler.post(delayMillisRunnable)
        }
    }

    override fun onCreate() {
        super.onCreate()

        // 위치 클라이언트 초기화
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        startLocationUpdates()

        // 센서 관리자 초기화
        handler = Handler(Looper.getMainLooper())
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
        stepSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }

        // 포그라운드 서비스 시작
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundServiceNotification()
        }
        Log.d("ExerciseService", "onCreate")
    }

    private fun startLocationUpdates() {
        val locationRequest = LocationRequest.create().apply {
            interval = delayMillis
            fastestInterval = delayMillis
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let {
                    latLng = LatLng(it.latitude, it.longitude)
                    Log.d("ExerciseService", "Location: $latLng")
                }
                isStart = true
            }
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, mainLooper)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startForegroundServiceNotification() {
        val channelId = "location_service_channel"
        val channelName = "Location Service"
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val channel =
            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)

        val notification: Notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Exercise Service")
            .setContentText("Recording location and steps in background")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .build()

        startForeground(1, notification)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_STEP_DETECTOR) {
            if (event.values[0] == 1.0f) {
                step++
            }
        }
    }

    private val delayMillisRunnable = object : Runnable {
        override fun run() {
            Log.d("ExerciseService", "Step: $step, Location: $latLng")
            val stepIntent = Intent("STEP_UPDATE").apply {
                putExtra("step", step)
            }
            sendBroadcast(stepIntent)
            step = 0
            val latLngIntent = Intent("LOCATION_UPDATE").apply {
                putExtra("lat", latLng.latitude)
                putExtra("lng", latLng.longitude)
            }
            sendBroadcast(latLngIntent)
            handler.postDelayed(this, delayMillis)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        // TODO("Not yet implemented")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        fusedLocationClient.removeLocationUpdates(locationCallback)
        sensorManager.unregisterListener(this, stepSensor)
        stopForeground(STOP_FOREGROUND_REMOVE)
        handler.removeCallbacks(delayMillisRunnable)
    }
}
