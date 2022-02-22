package com.example.cameraappviews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        if(collectibleList.size == 0) {
            populateCollectibles()
        }
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(applicationContext, 2)
            adapter = CardAdapter(collectibleList)


        }

    }

    override fun onStart() {
        super.onStart()
        val correct: Boolean = intent.getBooleanExtra("correct", false)
        if(correct){
            collectibleList.clear()
            Log.d("Start", "Started")
            collectibleList.add(Questionare.geniusBrain)
            collectibleList.add(Questionare.bugLab)
            collectibleList.add(Questionare.intergalacticExplorer)
            collectibleList.add(Questionare.ecoWorrior)
            collectibleList.add(Questionare.spacePioneer)
            collectibleList.add(Collectibles(R.drawable.planeticeimage3, "Prehistoric Caveman", "Prehistoric Exhibit", discovered = MutableLiveData(true)))
            collectibleList.add(Questionare.geniusBrain2)
            collectibleList.add(Questionare.bugLab2)
            collectibleList.add(Questionare.intergalacticExplorer2)
            collectibleList.add(Questionare.ecoWorrior)
            collectibleList.add(Questionare.spacePioneer)
            collectibleList.add(Questionare.preHistoricCaveman)
        }
        binding.recyclerView.adapter!!.notifyDataSetChanged()


    }

    override fun onResume() {
        Log.d("ONRESUME", "RESUMED")
        super.onResume()
        binding.recyclerView.adapter!!.notifyDataSetChanged()

    }

    private fun populateCollectibles() {
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