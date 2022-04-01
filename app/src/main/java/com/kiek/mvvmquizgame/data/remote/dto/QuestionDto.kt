package com.kiek.mvvmquizgame.data.remote.dto

import com.kiek.mvvmquizgame.domain.model.Question

data class QuestionDto(
    val airdate: String,
    val answer: String,
    val category: Category,
    val category_id: Int,
    val created_at: String,
    val game_id: Any,
    val id: Int,
    val invalid_count: Int,
    val question: String,
    val updated_at: String,
    val value: Int
)

fun QuestionDto.toQuestion() : Question{
    return Question(
        id = id,
        question = question,
        answer = answer
    )
}