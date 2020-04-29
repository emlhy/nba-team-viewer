package com.example.nbateamviewer.viewmodel

import androidx.lifecycle.ViewModel
import com.example.nbateamviewer.internal.lazyDeferred
import com.example.nbateamviewer.repository.NBARepository

class TeamViewModel(
    private val nbaRepository: NBARepository
) : ViewModel(){
    val teams by lazyDeferred {
        nbaRepository.getTeams()
    }
}