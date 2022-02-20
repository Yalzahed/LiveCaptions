package com.example.cameraappviews

class QuestionLibrary {
    private var questions = listOf<String>(
        "what kind of clothing did the stone age era wear?",
        "what was best invention from the stone age?",
        "what animal from the stone age is extinct now?",
        "did the stone age end?",
        "what stage came before the stone age?"
    )
    private var choices = listOf(
        listOf("animal skin", "silk", "cotton", "nothing"),
        listOf("fire","anger","singing","fashion"),
        listOf("tigers", "mammoth", "goat", "humans"),
        listOf("when they ran out of stone", "when they started using metals", "it didnt", "the mammoth took it away"),
        listOf("mud age", "nothing", "ice age", "DINOSAURS!")
    )

    var answers = listOf("animal skin","fire","mammoth","when they started using metals","ice age")

    fun getQuestion(a: Int): String{
        return questions[a]
    }
    fun getChoiceOne(a:Int): String{
        return choices[a][0]
    }
    fun getChoiceTwo(a:Int): String{
        return choices[a][1]
    }

    fun getChoiceThree(a:Int): String{
        return choices[a][2]
    }

    fun getChoiceFour(a:Int): String{
        return choices[a][3]
    }
}