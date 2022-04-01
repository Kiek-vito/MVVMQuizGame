package com.kiek.mvvmquizgame.presentation.question.components

sealed class Screen( val route : String) {
    object QuestionScreen : Screen("question_screen")
    object End : Screen("end")
}