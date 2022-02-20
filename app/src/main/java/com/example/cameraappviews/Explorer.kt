package com.example.cameraappviews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
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

        populateCollectibles()

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(applicationContext, 2)
            adapter = CardAdapter(collectibleList)
        }
    }

    private fun populateCollectibles() {
        collectibleList.add(Collectibles(R.drawable.bigbrain, "Genius Brain", "Human Biology Exhibit", discovered = true))
        collectibleList.add(Collectibles(R.drawable.buglab, "Apex Predator", "Bug Lab", discovered = true))
        collectibleList.add(Collectibles(R.drawable.space, "Intergalactic Explorer", "Space Exhibit", discovered = true))
        collectibleList.add(Collectibles(R.drawable.frog, "Eco Warrior", "Nature Exhibit", discovered = false))
        collectibleList.add(Collectibles(R.drawable.space_shuttle, "Space Pioneer", "Space Exhibit", discovered = false))
        collectibleList.add(Collectibles(R.drawable.planeticeimage3, "Prehistoric Caveman", "Prehistoric Exhibit", discovered = false))

        collectibleList.add(Collectibles(R.drawable.bigbrain, "Genius Brain", "Human Biology Exhibit", discovered = false))
        collectibleList.add(Collectibles(R.drawable.buglab, "Apex Predator", "Bug Lab", discovered = false))
        collectibleList.add(Collectibles(R.drawable.space, "Intergalactic Explorer", "Space Exhibit", discovered = false))
        collectibleList.add(Collectibles(R.drawable.frog, "Eco Warrior", "Nature Exhibit", discovered = false))
        collectibleList.add(Collectibles(R.drawable.space_shuttle, "Space Pioneer", "Space Exhibit", discovered = false))
        collectibleList.add(Collectibles(R.drawable.planeticeimage3, "Prehistoric Caveman", "Prehistoric Exhibit", discovered = false))
    }
}