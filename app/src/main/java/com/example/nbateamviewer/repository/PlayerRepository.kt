package com.example.nbateamviewer.repository

import androidx.lifecycle.LiveData
import com.example.nbateamviewer.data.PlayerEntry

interface PlayerRepository {
    suspend fun getPlayers(teamId: Int): LiveData<List<PlayerEntry>>
}