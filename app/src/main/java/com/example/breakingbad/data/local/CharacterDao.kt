package com.example.breakingbad.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.breakingbad.data.domain.Character

@Dao
interface CharacterDao {
    @Query("SELECT * FROM localcharacter order by name")
    fun getAllCharacters(): LiveData<List<LocalCharacter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCharacters(characters: List<LocalCharacter>)

}