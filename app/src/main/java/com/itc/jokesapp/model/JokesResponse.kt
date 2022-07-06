package com.itc.jokesapp.model


import com.google.gson.annotations.SerializedName

data class JokesResponse (
    @SerializedName("type")
    val type: String?,
    @SerializedName("value")
    val jokes: MutableList<Jokes>?
)