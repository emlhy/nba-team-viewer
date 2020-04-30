package com.example.nbateamviewer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nbateamviewer.repository.PlayerRepository

class PlayerViewModelFactory(
    private val playerRepository: PlayerRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlayerViewModel(playerRepository) as T
    }
}