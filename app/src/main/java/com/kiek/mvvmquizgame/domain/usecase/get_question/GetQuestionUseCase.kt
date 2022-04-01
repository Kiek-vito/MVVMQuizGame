package com.kiek.mvvmquizgame.domain.usecase.get_question

import com.kiek.mvvmquizgame.common.Resource
import com.kiek.mvvmquizgame.data.remote.dto.toQuestion
import com.kiek.mvvmquizgame.domain.model.Question
import com.kiek.mvvmquizgame.domain.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetQuestionUseCase @Inject constructor(
    private val repository: QuestionRepository
) {
    operator fun invoke(): Flow<Resource<List<Question>>> = flow {
        try {
            emit(Resource.Loading<List<Question>>())
            val question = repository.getQuestions().map { it.toQuestion() }
            emit(Resource.Success<List<Question>>(question))

        } catch (e: HttpException) {
            emit(Resource.Error<List<Question>>(e.localizedMessage ?: "Ошибка сервера"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Question>>("$e Нет доступа к интернету"))
        }

    }
}