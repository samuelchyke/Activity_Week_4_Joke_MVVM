package com.itc.jokesapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
//
//@Entity(tableName="joke_table")
//data class CacheJokes(
//
//    @PrimaryKey
//    val categories: List<String>,
//    val id: Int,
//    val joke: String
//
//)
//
//fun List<Jokes>.mapToCache() : List<CacheJokes> {
//    return this.map { joke ->
//        CacheJokes(
//            categories =  joke.categories ?: emptyList(),
//            id = joke.id ?: 999,
//            joke = joke.joke ?: "No Joke"
//        )
//    }
//
//}
