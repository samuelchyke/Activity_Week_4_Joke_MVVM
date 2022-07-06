package com.itc.jokesapp.util

import com.itc.jokesapp.model.Jokes
import com.itc.jokesapp.model.JokesResponse

sealed class UIState{

    object LOADING: UIState()
    data class SUCCESS(val response: JokesResponse): UIState()
    data class ERROR (val error: Exception): UIState()

}

// STATIC
enum class STATE( val response: String) {
    LOADING(""),
    SUCCESS(""),
    ERROR("")
}
