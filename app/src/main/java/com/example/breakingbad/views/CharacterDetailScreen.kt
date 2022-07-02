package com.example.breakingbad.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.breakingbad.R
import com.example.breakingbad.data.domain.Character

@Composable
fun CharacterDetailScreen(
    character: Character
){
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = character.name,
                style = MaterialTheme.typography.h3
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp,10.dp,10.dp,10.dp)
        ){
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(top = 15.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 8.dp),
                    text = "Attributes",
                    style = MaterialTheme.typography.h4
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 6.dp),
                    text = "Character Id: "+character.char_id,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 6.dp),
                    text = "Name: "+character.name,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 6.dp),
                    text = "Birthday: "+character.birthday,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 6.dp),
                    text = "Status: "+character.status,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 6.dp),
                    text = "Nickname: "+character.nickname,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 6.dp),
                    text = "Portrayed: "+character.portrayed,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                modifier = Modifier
                    .weight(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(){
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth(),
                        alignment = Alignment.Center,
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(character.img)
                            .build(),
                        placeholder = painterResource(R.drawable.placeholder),
                        contentDescription = character.name,
                    )
                }
            }
        }
    }
}