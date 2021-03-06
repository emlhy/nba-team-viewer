package com.example.nbateamviewer.repository

import androidx.lifecycle.LiveData
import com.example.nbateamviewer.data.NBAData
import com.example.nbateamviewer.data.PlayerEntry
import com.example.nbateamviewer.data.TeamEntry
import com.example.nbateamviewer.db.PlayerDao
import com.example.nbateamviewer.db.TeamDao
import com.example.nbateamviewer.network.NBADataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeamRepositoryImpl(
    private val teamDao: TeamDao,
    private val playerDao: PlayerDao,
    private val nbaDataSource: NBADataSource
) : TeamRepository {

    init {
        nbaDataSource.downloadedNBAData.observeForever { newNBAData ->
            persistFetchedNBAData(newNBAData)
        }
    }

    override suspend fun getTeams(): LiveData<List<TeamEntry>> {
        return withContext(Dispatchers.IO){
            fetchNBAData()
            return@withContext teamDao.getTeams()
        }
    }

    private fun persistFetchedNBAData(fetchedNBAData: NBAData) {
        GlobalScope.launch(Dispatchers.IO) {
            for (team in fetchedNBAData) {
                teamDao.upsert(TeamEntry(team.fullName, team.id, team.losses, team.wins))
                for (player in team.players) {
                    playerDao.upsert(PlayerEntry(player.firstName, player.id, player.lastName, player.number, player.position, team.id))
                }
            }
        }
    }

    private suspend fun fetchNBAData() {
        nbaDataSource.fetchNBAData()
    }
}