package com.kiek.mvvmquizgame.presentation.question

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kiek.mvvmquizgame.presentation.question.components.Screen

@Composable
fun End(
    navController: NavController,
){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                navController.navigate("question_screen")

            },
        contentAlignment = Alignment.Center,

    ){
        Column() {
            Text(text = "Quiz ended, click to restart ")

    }
}

}