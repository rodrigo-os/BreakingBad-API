package com.example.breakingbad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.breakingbad.ui.theme.BreakingBadTheme
import com.example.breakingbad.views.CharacterDetailScreen
import com.example.breakingbad.views.CharacterListScreen
import com.example.breakingbad.views.CharacterVMFactory
import com.example.breakingbad.views.CharactersViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<CharactersViewModel> {
            CharacterVMFactory(
                (this.applicationContext as BreakingBadApplication).repository
            )
        }
        setContent {
            BreakingBadTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BreakingBad(viewModel)
                }
            }
        }
    }
}

@Composable
fun BreakingBad(
    viewModel: CharactersViewModel
) {
    val navController = rememberNavController()
    Scaffold() {
        NavHost(navController = navController, startDestination = "characterList") {
            composable(route = "characterList") {
                CharacterListScreen(
                    charactersViewModel = viewModel,
                    navController
                )
            }
            composable(
                route = "characterList/{char_id}",
                arguments = listOf(navArgument("char_id") {
                    defaultValue = -1
                    type = NavType.IntType
                })
            ) {
                val char_id = it.arguments?.getInt("char_id") ?: -1
                val character = viewModel.getCharacterById(char_id)
                CharacterDetailScreen(character)
            }
        }
    }
}