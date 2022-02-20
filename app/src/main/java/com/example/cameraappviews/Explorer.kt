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
            finish()
            startActivity(Intent(this, Scanner::class.java))
        }
        binding.btnHome2.setOnClickListener {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }

        populateCollectibles()

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(applicationContext, 2)
            adapter = CardAdapter(collectibleList)



        }
    }

    private fun populateCollectibles() {
        collectibleList.clear()
        collectibleList.add(Questionare.geniusBrain)
        collectibleList.add(Questionare.bugLab)
        collectibleList.add(Questionare.intergalacticExplorer)
        collectibleList.add(Questionare.ecoWorrior)
        collectibleList.add(Questionare.spacePioneer)
        collectibleList.add(Questionare.preHistoricCaveman)
        collectibleList.add(Questionare.geniusBrain2)
        collectibleList.add(Questionare.bugLab2)
        collectibleList.add(Questionare.intergalacticExplorer2)
        collectibleList.add(Questionare.ecoWorrior)
        collectibleList.add(Questionare.spacePioneer)
        collectibleList.add(Questionare.preHistoricCaveman)

    }
}