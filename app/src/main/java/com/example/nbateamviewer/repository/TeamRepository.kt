package com.example.nbateamviewer.repository

import androidx.lifecycle.LiveData
import com.example.nbateamviewer.data.PlayerEntry
import com.example.nbateamviewer.data.TeamEntry

interface TeamRepository {
    suspend fun getTeams(): LiveData<List<TeamEntry>>
}