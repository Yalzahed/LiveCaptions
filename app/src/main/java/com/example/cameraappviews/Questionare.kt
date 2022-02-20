package com.example.cameraappviews

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.ColorRes
import com.example.cameraappviews.databinding.ActivityMainBinding
import com.example.cameraappviews.databinding.ActivityQuestionareBinding

private var questionLibrary  = QuestionLibrary()
    private var questionNumber = 0
    private var correct = 0
private lateinit var binding: ActivityQuestionareBinding
class Questionare : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
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

    private fun getQuestions(){
        if(questionNumber == 4){
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