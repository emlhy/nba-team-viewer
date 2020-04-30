package com.example.nbateamviewer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nbateamviewer.data.PlayerEntry
import com.example.nbateamviewer.internal.lazyDeferred
import com.example.nbateamviewer.repository.TeamRepository
import kotlinx.coroutines.Deferred

class TeamViewModel(
    private val teamRepository: TeamRepository
) : ViewModel(){
    val teams by lazyDeferred {
        teamRepository.getTeams()
    }
}