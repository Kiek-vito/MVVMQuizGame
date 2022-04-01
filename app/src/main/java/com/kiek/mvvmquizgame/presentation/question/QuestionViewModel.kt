package com.kiek.mvvmquizgame.presentation.question

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiek.mvvmquizgame.common.Resource
import com.kiek.mvvmquizgame.domain.model.Question
import com.kiek.mvvmquizgame.domain.usecase.get_question.GetQuestionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val getQuestionUseCase: GetQuestionUseCase
): ViewModel(){

    private val _state = mutableStateOf(QuestionState())
    val state: State<QuestionState> = _state
    lateinit var listQuestion: List<Question>
    val answer = mutableStateOf("")
    val trueAnswer = mutableStateOf("")
    val showDialog = mutableStateOf(false)
    val isButtonEnable = mutableStateOf(false)
    val isAnswered = mutableStateOf(false)
    val isAnswerTrue  = mutableStateOf(false)

    var i = 0

    init {
        getQuestion()
    }

    fun showDialog(){
        //trueAnswer.value = _state.value.question.answer
        _state.value = QuestionState(showDialog = true, questions = listQuestion)
    }

    fun dismissDialog(){
        _state.value = QuestionState(showDialog = false, questions = listQuestion)

    }

  /*  fun nextQuestion(){

        if(listQuestion.isNotEmpty() && i<listQuestion.size) {
            _state.value = QuestionState(question = listQuestion[i+1])
            i++
        }

    }*/

    private fun getQuestion(){
        getQuestionUseCase().onEach { resource ->
            when(resource){

                is Resource.Loading -> {
                    _state.value = QuestionState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = QuestionState(error = resource.message ?: "Неизвестная ошибка")
                }
                is Resource.Success -> {
                    listQuestion = resource.data ?: emptyList()
                    _state.value = QuestionState(questions = resource.data ?: emptyList() )
                }
            }

        }.launchIn(viewModelScope)
    }
}