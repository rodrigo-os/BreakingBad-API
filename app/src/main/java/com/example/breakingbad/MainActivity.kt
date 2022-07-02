package com.example.breakingbad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.breakingbad.ui.theme.BreakingBadTheme
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
    CharacterListScreen(charactersViewModel = viewModel)
}