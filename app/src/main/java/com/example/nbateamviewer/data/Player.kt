package com.example.nbateamviewer.data

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("first_name")
    val firstName: String,
    val id: Int,
    @SerializedName("last_name")
    val lastName: String,
    val number: Int,
    val position: String
)