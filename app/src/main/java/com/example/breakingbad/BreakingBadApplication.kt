package com.example.breakingbad

import android.app.Application
import com.example.breakingbad.data.local.CharactersDatabase
import com.example.breakingbad.data.repository.CharacterRepository

class BreakingBadApplication: Application() {

    private val database: CharactersDatabase by lazy {
        CharactersDatabase.getInstance(this)
    }

    val repository: CharacterRepository by lazy {
        CharacterRepository(database.characterDao())
    }
}