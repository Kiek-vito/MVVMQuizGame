package com.kiek.mvvmquizgame.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kiek.mvvmquizgame.presentation.question.End
import com.kiek.mvvmquizgame.presentation.question.QuestionScreen
import com.kiek.mvvmquizgame.presentation.question.components.Screen
import com.kiek.mvvmquizgame.presentation.ui.theme.MVVMQuizGameTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMQuizGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.QuestionScreen.route
                    ){
                        composable(
                            route = Screen.QuestionScreen.route
                        ){
                            QuestionScreen(navController)
                        }

                        composable(
                            route = Screen.End.route
                        ){
                            End(navController)
                        }


                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MVVMQuizGameTheme {
    }
}