package com.example.cameraappviews

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        binding.answer1.setOnClickListener {
            if (questionLibrary.answers.contains(binding.answer1.text)){
                binding.answer1.setBackgroundColor(Color.GREEN)

            }
            else{
                binding.answer1.setBackgroundColor(Color.RED)
            }
            Thread.sleep(500)
            getQuestions()
        }
        binding.answer2.setOnClickListener {
            if (questionLibrary.answers.contains(binding.answer2.text)){
                binding.answer2.setBackgroundColor(Color.GREEN)

            }
            else{
                binding.answer2.setBackgroundColor(Color.RED)
            }
            Thread.sleep(500)
            getQuestions()
        }

        binding.answer3.setOnClickListener {
            if (questionLibrary.answers.contains(binding.answer3.text)){
                binding.answer3.setBackgroundColor(Color.GREEN)

            }
            else{
                binding.answer3.setBackgroundColor(Color.RED)
            }
            Thread.sleep(500)
            getQuestions()
        }
        binding.answer4.setOnClickListener {
            if (questionLibrary.answers.contains(binding.answer4.text)){
                binding.answer4.setBackgroundColor(Color.GREEN)
            }
            else{
                binding.answer4.setBackgroundColor(Color.RED)
            }
            Thread.sleep(500)
            getQuestions()
        }
    }

    private fun getQuestions(){


        binding.question.text = questionLibrary.getQuestion(questionNumber)
        binding.answer1.text = questionLibrary.getChoiceOne(questionNumber)
        binding.answer2.text = questionLibrary.getChoiceTwo(questionNumber)
        binding.answer3.text = questionLibrary.getChoiceThree(questionNumber)
        binding.answer4.text = questionLibrary.getChoiceFour(questionNumber)
        questionNumber += 1
    }


}