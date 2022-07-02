package com.example.breakingbad.data.source

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import com.example.breakingbad.data.domain.Character

private const val BASE_URL = "https://www.breakingbadapi.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BreakingBadApiService {
    @GET("api/characters")
    suspend fun getCharacters(): List<SourceCharacter>
}

object BreakingBadApi {
    val retrofitService: BreakingBadApiService by lazy{
        retrofit.create(BreakingBadApiService::class.java)
    }
}