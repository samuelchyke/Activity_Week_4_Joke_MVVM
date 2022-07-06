package com.itc.jokesapp.repository

import com.itc.jokesapp.api.JokeServiceApi
import com.itc.jokesapp.model.JokesResponse
import retrofit2.Response

interface JokesRepo {
   suspend fun getJokes(): Response<JokesResponse>
   suspend fun getRandomJokes(): Response<JokesResponse>
   suspend fun getCustomJoke(): Response<JokesResponse>
}

class JokesRepoImpl (
   private val jokeServiceApi: JokeServiceApi
        ) : JokesRepo{

   override suspend fun getJokes(): Response<JokesResponse> {
      return jokeServiceApi.getJokes()
   }

   override suspend fun getRandomJokes(): Response<JokesResponse> {
      return jokeServiceApi.getRandomJokes()
   }

   override suspend fun getCustomJoke(): Response<JokesResponse> {
      return jokeServiceApi.getCustomJoke()
   }

}
