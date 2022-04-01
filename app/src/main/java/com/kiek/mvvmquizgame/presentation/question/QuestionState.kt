package com.kiek.mvvmquizgame.presentation.question

import com.kiek.mvvmquizgame.domain.model.Question

data class QuestionState (
    val isLoading: Boolean = false,
    val questions: List<Question> = emptyList(),
    val error: String = "",
    val showDialog: Boolean = false

)