package com.example.nbateamviewer.repository

import androidx.lifecycle.LiveData
import com.example.nbateamviewer.data.PlayerEntry
import com.example.nbateamviewer.db.PlayerDao
import kotlinx.coroutines.withContext

class PlayerRepositoryImpl(
    private val playerDao: PlayerDao
) : PlayerRepository {

    override suspend fun getPlayers(teamId: Int): LiveData<List<PlayerEntry>> {
        return withContext(kotlinx.coroutines.Dispatchers.IO) {
            return@withContext playerDao.getPlayers(teamId)
        }
    }
}