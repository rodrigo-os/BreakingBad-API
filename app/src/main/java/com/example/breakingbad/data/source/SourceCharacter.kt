package com.example.breakingbad.data.source

import com.example.breakingbad.data.domain.Character
import com.example.breakingbad.data.local.LocalCharacter

data class SourceCharacterContainer(
    val sourceCharacters: List<SourceCharacter>
)

data class SourceCharacter(
    val char_id: Int,
    val birthday: String,
    val name: String,
    val nickname: String,
    val img: String,
    val status: String,
    val portrayed: String,
)

fun SourceCharacterContainer.asDomainModel(): List<Character> {
    return sourceCharacters.map {
        Character(
            char_id = it.char_id,
            birthday = it.birthday,
            name = it.name,
            nickname = it.nickname,
            img = it.img,
            status = it.status,
            portrayed = it.portrayed,
        )
    }
}

fun SourceCharacterContainer.asLocalModel(): List<LocalCharacter> {
    return sourceCharacters.map {
        LocalCharacter(
            char_id = it.char_id,
            birthday = it.birthday,
            name = it.name,
            nickname = it.nickname,
            img = it.img,
            status = it.status,
            portrayed = it.portrayed,
        )
    }
}