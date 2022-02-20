package com.example.cameraappviews

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.cameraappviews.databinding.ActivityMainBinding
import com.example.cameraappviews.databinding.ActivityQuestionareBinding

private var questionLibrary  = QuestionLibrary()
var questionNumber = 0

private lateinit var binding: ActivityQuestionareBinding

class Questionare : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        questionNumber = 0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionare)
        binding = ActivityQuestionareBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getQuestions()
        binding.answer1.setOnClickListener {
            if (questionLibrary.answers.contains(binding.answer1.text)){
                binding.answer1.setBackgroundColor(Color.GREEN)

            }
            else{
                binding.answer1.setBackgroundColor(Color.RED)
            }
            Thread.sleep(300)
            getQuestions()
        }
        binding.answer2.setOnClickListener {
            if (questionLibrary.answers.contains(binding.answer2.text)){
                binding.answer2.setBackgroundColor(Color.GREEN)

            }
            else{
                binding.answer2.setBackgroundColor(Color.RED)
            }
            Thread.sleep(300)
            getQuestions()
        }

        binding.answer3.setOnClickListener {
            if (questionLibrary.answers.contains(binding.answer3.text)){
                binding.answer3.setBackgroundColor(Color.GREEN)

            }
            else{
                binding.answer3.setBackgroundColor(Color.RED)
            }
            Thread.sleep(300)
            getQuestions()
        }
        binding.answer4.setOnClickListener {
            if (questionLibrary.answers.contains(binding.answer4.text)){
                binding.answer4.setBackgroundColor(Color.GREEN)
            }
            else{
                binding.answer4.setBackgroundColor(Color.RED)
            }
            Thread.sleep(300)
            getQuestions()
        }



    }
    companion object cavemanStatus{
        var geniusBrain = Collectibles(R.drawable.bigbrain, "Genius Brain", "Human Biology Exhibit", discovered = MutableLiveData(true))
        var bugLab = Collectibles(R.drawable.buglab, "Apex Predator", "Bug Lab", discovered = MutableLiveData(true))
        var intergalacticExplorer = Collectibles(R.drawable.space, "Intergalactic Explorer", "Space Exhibit", discovered = MutableLiveData(true))
        var ecoWorrior = Collectibles(R.drawable.frog, "Eco Warrior", "Nature Exhibit", discovered = MutableLiveData(false))
        var spacePioneer = Collectibles(R.drawable.space_shuttle, "Space Pioneer", "Space Exhibit", discovered = MutableLiveData(false))
        var  preHistoricCaveman = Collectibles(R.drawable.planeticeimage3, "Prehistoric Caveman", "Prehistoric Exhibit", discovered = MutableLiveData(false))
        var geniusBrain2 = Collectibles(R.drawable.bigbrain, "Genius Brain", "Human Biology Exhibit", discovered = MutableLiveData(false))
        var bugLab2 = Collectibles(R.drawable.buglab, "Apex Predator", "Bug Lab", discovered = MutableLiveData(false))
        var intergalacticExplorer2 = Collectibles(R.drawable.space, "Intergalactic Explorer", "Space Exhibit", discovered = MutableLiveData(false))
        var  preHistoricCaveman2 = Collectibles(R.drawable.planeticeimage3, "Prehistoric Caveman", "Prehistoric Exhibit", discovered = MutableLiveData(false))
        var cavemanDiscovered = false
    }

    @SuppressLint("ResourceType")
    private fun getQuestions(){
        if(questionNumber > 4){
            cavemanDiscovered = true
            startActivity(Intent(this, Scanner::class.java))
        }
        resetButtons()
        binding.question.text = questionLibrary.getQuestion(questionNumber)
        binding.answer1.text = questionLibrary.getChoiceOne(questionNumber)
        binding.answer2.text = questionLibrary.getChoiceTwo(questionNumber)
        binding.answer3.text = questionLibrary.getChoiceThree(questionNumber)
        binding.answer4.text = questionLibrary.getChoiceFour(questionNumber)
        questionNumber += 1
    }
    private fun resetButtons(){
        binding.answer1.setBackgroundColor(Color.parseColor("#6200EE"))
        binding.answer2.setBackgroundColor(Color.parseColor("#6200EE"))
        binding.answer3.setBackgroundColor(Color.parseColor("#6200EE"))
        binding.answer4.setBackgroundColor(Color.parseColor("#6200EE"))

    }

}