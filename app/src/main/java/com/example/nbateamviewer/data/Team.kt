package com.example.nbateamviewer.data

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("full_name")
    val fullName: String,
    val id: Int,
    val losses: Int,
    val players: List<Player>,
    val wins: Int
)