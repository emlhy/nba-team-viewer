package com.example.nbateamviewer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nbateamviewer.repository.NBARepository

class TeamViewModelFactory(
    private val nbaRepository: NBARepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TeamViewModel(nbaRepository) as T
    }
}