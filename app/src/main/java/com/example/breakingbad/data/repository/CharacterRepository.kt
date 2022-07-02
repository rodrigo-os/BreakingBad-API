package com.example.breakingbad.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.breakingbad.data.domain.Character
import com.example.breakingbad.data.local.CharacterDao
import com.example.breakingbad.data.local.asDomainModel
import com.example.breakingbad.data.source.BreakingBadApi
import com.example.breakingbad.data.source.SourceCharacterContainer
import com.example.breakingbad.data.source.asLocalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepository(private val characterDao: CharacterDao) {
    val characters: LiveData<List<Character>> = Transformations.map(
        characterDao.getAllCharacters()
    ){
        it.asDomainModel()
    }

    suspend fun refreshCharacters(){
        withContext(Dispatchers.IO){
            val characters = BreakingBadApi.retrofitService.getCharacters()
            val charactersContainer = SourceCharacterContainer(characters)
            characterDao.insertAllCharacters(charactersContainer.asLocalModel())
        }
    }
}