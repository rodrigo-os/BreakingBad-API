package com.example.breakingbad.views

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.network.BreakingBadApi
import kotlinx.coroutines.launch
import com.example.breakingbad.data.Character

class CharactersViewModel : ViewModel() {
    private val _characterList = MutableLiveData<List<Character>>()
    val characterList: LiveData<List<Character>>
        get() = _characterList

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            try {
                val listResult = BreakingBadApi.retrofitService.getCharacters()
                _characterList.value = listResult
            } catch (e: Exception) {
                _characterList.value = null
                Log.d("GetCharacters", "${e.message}")
            }
        }
    }
}