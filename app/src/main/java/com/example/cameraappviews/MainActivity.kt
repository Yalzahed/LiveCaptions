package com.example.cameraappviews

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
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
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import java.lang.Exception
import java.nio.ByteBuffer
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

//typealias LumaListener = (luma: Double) -> Unit
//typealias BarcodeListener = (barcode: String) -> Unit


// lateinit means we want to use these objects at some point but we dont want to initalize them right now
private lateinit var binding: ActivityMainBinding
private lateinit var cameraExecutor: ExecutorService

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.scanQr.setOnClickListener {
            startActivity(Intent(this, Scanner::class.java))
        }
        binding.btnExplore.setOnClickListener {
            startActivity(Intent(this, Explorer::class.java))
        }
    }
}
