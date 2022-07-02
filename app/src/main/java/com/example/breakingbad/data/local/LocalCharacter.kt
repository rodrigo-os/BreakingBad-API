package com.example.breakingbad.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.breakingbad.data.domain.Character

@Entity
data class LocalCharacter(
    @PrimaryKey
    val char_id: Int,
    val birthday: String,
    val name: String,
    val nickname: String,
    val img: String,
    val status: String,
)

fun List<LocalCharacter>.asDomainModel(): List<Character> {
    return map{
        Character(
            char_id = it.char_id,
            birthday = it.birthday,
            name = it.name,
            nickname = it.nickname,
            img = it.img,
            status = it.status
        )
    }
}