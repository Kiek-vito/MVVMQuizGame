package com.kiek.mvvmquizgame.data.remote

import com.kiek.mvvmquizgame.data.remote.dto.QuestionDto
import retrofit2.http.GET
import retrofit2.http.Path

interface JServiceApi {

    @GET("/api/random?count=2")
    suspend fun getQuestions() : List<QuestionDto>
}