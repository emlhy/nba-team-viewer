package com.example.nbateamviewer.network

import androidx.lifecycle.LiveData
import com.example.nbateamviewer.data.NBAData

interface NBADataSource {
    val downloadedNBAData: LiveData<NBAData>

    suspend fun fetchNBAData()
}