package com.example.nbateamviewer.repository

import androidx.lifecycle.LiveData
import com.example.nbateamviewer.data.TeamEntry

interface NBARepository {
    suspend fun getTeams(): LiveData<List<TeamEntry>>
}