package com.example.cameraappviews

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.cameraappviews.databinding.ActivityMainBinding
import java.lang.Exception

// lateinit means we want to use these objects at some point but we dont want to initalize them right now
private lateinit var binding: ActivityMainBinding
private lateinit var imageCapture: ImageCapture

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // checks if the permissions are granted else asks for permissions (only asks for permissions once)
        if (allPermissionGranted()) {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this,
                Constants.REQUIRED_PERMISSIONS,
                Constants.REQUEST_CODE_PERMISSIONS
            )
        }
    }

    private fun startCamera() {
        // request a camera provider
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        // check for camera availability
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            // create a preview and bind it to the preview view in the xml
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(
                        binding.viewFinder.surfaceProvider
                    )
                }
            // select which camera we want to use
            var cameraSelector: CameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
                .build()


            // bind the camera and and any usecases in the to the current lifecycle
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview)
            } catch (e: Exception) {
                Log.d(Constants.TAG, "cameraStart Fail", e)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    // this says if the user has already made a decision on the permission then display this
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.REQUEST_CODE_PERMISSIONS) {
            if (allPermissionGranted()) {

            } else {
                Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    // this checks if the user has granted permission the .all { checks if all the required permissions are permission granted
    private fun allPermissionGranted() =
        Constants.REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                baseContext, it
            ) == PackageManager.PERMISSION_GRANTED
        }
}
