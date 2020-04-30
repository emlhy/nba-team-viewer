package com.example.nbateamviewer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nbateamviewer.data.PlayerEntry
import com.example.nbateamviewer.internal.lazyDeferred
import com.example.nbateamviewer.repository.PlayerRepository
import kotlinx.coroutines.Deferred

class PlayerViewModel(
    private val playerRepository: PlayerRepository
) : ViewModel(){
    fun players(teamId: Int): Deferred<LiveData<List<PlayerEntry>>> {
        val players by lazyDeferred {
            playerRepository.getPlayers(teamId)
        }
        return players
    }
}