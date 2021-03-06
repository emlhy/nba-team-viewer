package com.example.nbateamviewer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nbateamviewer.repository.TeamRepository

class TeamViewModelFactory(
    private val teamRepository: TeamRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TeamViewModel(teamRepository) as T
    }
}