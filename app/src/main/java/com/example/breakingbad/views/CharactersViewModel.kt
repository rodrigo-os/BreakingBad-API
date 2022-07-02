package com.example.breakingbad.views

import android.util.Log
import androidx.lifecycle.*
import com.example.breakingbad.data.source.BreakingBadApi
import kotlinx.coroutines.launch
import com.example.breakingbad.data.domain.Character
import com.example.breakingbad.data.repository.CharacterRepository
import java.io.IOException
import java.lang.IllegalArgumentException

class CharactersViewModel(private val repository: CharacterRepository) : ViewModel() {

    init {
        if(repository.characters.value.isNullOrEmpty()){
            refreshDataFromRepository()
        }
    }

    val characters = repository.characters

    private val _eventNetworkError = MutableLiveData<String>("")

    private fun refreshDataFromRepository(){
        viewModelScope.launch {
            try {
                repository.refreshCharacters()
                _eventNetworkError.value = ""
            }catch (networkError: IOException){
                Log.d("Error", "${networkError.message}")
                _eventNetworkError.value = networkError.message
            }
        }
    }

}

class CharacterVMFactory(private val repository: CharacterRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CharactersViewModel::class.java))
            return CharactersViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}