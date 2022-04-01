package com.kiek.mvvmquizgame.domain.repository

import com.kiek.mvvmquizgame.data.remote.dto.QuestionDto
import com.kiek.mvvmquizgame.domain.model.Question

interface QuestionRepository {

    suspend fun getQuestions(): List<QuestionDto>
}