package com.example.cameraappviews

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.cameraappviews.databinding.ActivityMainBinding
import com.example.cameraappviews.databinding.ActivityScannerBinding
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import java.lang.Exception
import java.nio.ByteBuffer
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

typealias LumaListener = (luma: Double) -> Unit
typealias BarcodeListener = (barcode: String) -> Unit


// lateinit means we want to use these objects at some point but we dont want to initalize them right now
private lateinit var binding: ActivityScannerBinding
private lateinit var cameraExecutor: ExecutorService

class Scanner : AppCompatActivity(){override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityScannerBinding.inflate(layoutInflater)
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
    cameraExecutor = Executors.newSingleThreadExecutor()

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
                .also { _preview ->
                    _preview.setSurfaceProvider(
                        binding.viewFinder.surfaceProvider
                    )
                }
            val imageAnalysis = ImageAnalysis.Builder()
                .setTargetResolution(Size(binding.viewFinder.width, binding.viewFinder.height))
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, QrCodeAnalyzer { qrResult ->
                        binding.viewFinder.post {
                            Log.d("QRCodeAnalyzer", "Barcode scanned: ${qrResult.text}")

                        }
                    })
                }

            // select which camera we want to use
            var cameraSelector: CameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build()


            // bind the camera and and any usecases in the to the current lifecycle
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview,imageAnalysis)
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

    private class LuminosityAnalyzer(private val listener: LumaListener) : ImageAnalysis.Analyzer {

        private fun ByteBuffer.toByteArray(): ByteArray {
            rewind()    // Rewind the buffer to zero
            val data = ByteArray(remaining())
            get(data)   // Copy the buffer into a byte array
            return data // Return the byte array
        }

        override fun analyze(image: ImageProxy) {
            val buffer = image.planes[0].buffer
            val data = buffer.toByteArray()
            val pixels = data.map { it.toInt() and 0xFF }
            val luma = pixels.average()


            listener(luma)

            image.close()
        }
    }

    class BarcodeAnalyzer(private val barcodeListener: BarcodeListener) : ImageAnalysis.Analyzer {
        // Get an instance of BarcodeScanner
        private val scanner = BarcodeScanning.getClient()

        @SuppressLint("UnsafeExperimentalUsageError", "UnsafeOptInUsageError")
        override fun analyze(imageProxy: ImageProxy) {
            val mediaImage = imageProxy.image
            if (mediaImage != null) {
                val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
                // Pass image to the scanner and have it do its thing
                scanner.process(image)
                    .addOnSuccessListener { barcodes ->
                        // Task completed successfully
                        for (barcode in barcodes) {
                            barcodeListener(barcode.rawValue ?: "")
                        }
                    }
                    .addOnFailureListener {
                        // You should really do something about Exceptions
                    }
                    .addOnCompleteListener {
                        // It's important to close the imageProxy
                        imageProxy.close()
                    }
            }
        }
    }
}
