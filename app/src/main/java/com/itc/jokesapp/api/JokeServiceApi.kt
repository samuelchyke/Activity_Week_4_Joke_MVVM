package com.itc.jokesapp.api

import com.itc.jokesapp.model.JokesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface JokeServiceApi {

    @GET(JOKES_PATH)
    suspend fun getJokes():Response<JokesResponse>

    @GET(RANDOM_JOKES_PATH)
    suspend fun getRandomJokes():Response<JokesResponse>

    @GET(CUSTOM_JOKE_PATH)
    suspend fun getCustomJoke(
        @Query("firstName") firstName: String? ,
        @Query("lastName") lastName: String?
    ):Response<JokesResponse>

    companion object{

        const val BASE_URL = "https://api.icndb.com/"
        private const val JOKES_PATH = "jokes"
        private const val RANDOM_JOKES_PATH = "jokes/random/50"
        private const val CUSTOM_JOKE_PATH = "jokes/random/1"

    }

}
