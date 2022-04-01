package com.kiek.mvvmquizgame.di

import com.kiek.mvvmquizgame.common.Constants
import com.kiek.mvvmquizgame.data.remote.JServiceApi
import com.kiek.mvvmquizgame.data.repository.QuestionRepositoryImpl
import com.kiek.mvvmquizgame.domain.repository.QuestionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideJServiceApi(): JServiceApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideQuestionRepository(api: JServiceApi) : QuestionRepository{
        return QuestionRepositoryImpl(api)
    }
}