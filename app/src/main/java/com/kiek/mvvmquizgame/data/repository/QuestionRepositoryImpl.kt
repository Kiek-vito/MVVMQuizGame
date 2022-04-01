package com.kiek.mvvmquizgame.data.repository

import com.kiek.mvvmquizgame.data.remote.JServiceApi
import com.kiek.mvvmquizgame.data.remote.dto.QuestionDto
import com.kiek.mvvmquizgame.domain.model.Question
import com.kiek.mvvmquizgame.domain.repository.QuestionRepository
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val api : JServiceApi
): QuestionRepository {

    override suspend fun getQuestions(): List<QuestionDto> {
        return api.getQuestions()
    }
}