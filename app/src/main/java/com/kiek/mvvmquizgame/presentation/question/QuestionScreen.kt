package com.kiek.mvvmquizgame.presentation.question

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kiek.mvvmquizgame.domain.model.Question

@Composable
fun QuestionScreen(
    navController: NavController,
    viewModel: QuestionViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    var numberQuestion by rememberSaveable { mutableStateOf(0) }
    var isButtonEnable by rememberSaveable { mutableStateOf(false) }
    var isAnswerTrue by rememberSaveable { mutableStateOf(false) }
    var answer by rememberSaveable { mutableStateOf("") }
    var question: Question = (Question(0, "", ""))
    val dialogColor: Color by animateColorAsState(
        if (isAnswerTrue) Color.Green else Color.Red
    )
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            state.questions.let { list ->
                if (list.isNotEmpty()) {
                    question = list[numberQuestion]

                    Text(text = question.question,
                        textAlign = TextAlign.Center)

                    TextField(
                        value = answer,
                        textStyle = TextStyle(fontSize = 25.sp),
                        onValueChange = {
                            answer = it
                            if (it.isNotEmpty())
                                isButtonEnable = true
                        },
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .padding(top = 15.dp)
                    )

                    Button(
                        onClick = {
                            isAnswerTrue = question.answer == answer
                            viewModel.showDialog()
                        },
                        enabled = isButtonEnable,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .padding(top = 15.dp)
                    ) {
                        Text(text = "Answer the question")
                    }
                }
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        if (state.showDialog) {
            AlertDialog(
                onDismissRequest = {
                    viewModel.dismissDialog()
                    if (state.questions.size-1 > numberQuestion) numberQuestion++
                    else navController.navigate("end")
                    answer = ""
                    isButtonEnable = false
                },
                text = {
                    Column() {
                        Text(text = "Your answer is $answer")
                        Text(text = "True answer is ${question.answer}")
                    }

                },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.dismissDialog()
                            if (state.questions.size-1 > numberQuestion) numberQuestion++
                            else navController.navigate("end")
                            answer = ""
                            isButtonEnable = false
                        }) {
                        Text("ok")
                    }
                },
                backgroundColor = dialogColor
            )
        }
    }
}







