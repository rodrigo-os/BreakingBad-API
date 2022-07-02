package com.example.breakingbad.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.breakingbad.data.domain.Character
import com.example.breakingbad.R

@Composable
fun CharacterListScreen(
    charactersViewModel: CharactersViewModel,
    navController: NavController
) {
    val charactersList by charactersViewModel.characterList.observeAsState(listOf())
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp, 0.dp, 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Breaking Bad",
                style = MaterialTheme.typography.h4.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        CharacterList(
            charactersList,
            navController
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterList(
    characterList: List<Character>,
    navController: NavController
) {
    LazyVerticalGrid(
        modifier = Modifier.background(Color.LightGray),
        cells = GridCells.Fixed(2)
    ) {
        items(characterList) {
            CharacterEntry(it) {
                navController.navigate("characterList/${it.char_id}")
            }
        }
    }
}

@Composable
fun CharacterEntry(
    character: Character,
    characterDetail: () -> Unit
) {
    val density = LocalDensity.current.density
    val width = remember { mutableStateOf(0F) }
    val height = remember { mutableStateOf(0F) }
    Card(
        modifier = Modifier
            .padding(6.dp)
            .clickable { characterDetail() },
        elevation = 8.dp

    ) {
        Box() {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.img)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.placeholder),
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned {
                        width.value = it.size.width / density
                        height.value = it.size.height / density
                    }
            )
            Box(
                modifier = Modifier
                    .size(width.value.dp, height.value.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black),
                            80F,
                            700F,
                        )
                    )
            )
            Text(
                text = character.name,
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                style = MaterialTheme.typography.h6.copy(
                    color = Color.White, fontWeight = FontWeight.Bold
                )
            )
        }
    }
}