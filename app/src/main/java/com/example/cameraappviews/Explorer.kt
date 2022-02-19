package com.example.cameraappviews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cameraappviews.databinding.ActivityExplorerBinding
import com.example.cameraappviews.databinding.ActivityMainBinding

private lateinit var binding: ActivityExplorerBinding

class Explorer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explorer)

        binding = ActivityExplorerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.scanQr2.setOnClickListener {
            startActivity(Intent(this, Scanner::class.java))
        }
        binding.btnHome2.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}