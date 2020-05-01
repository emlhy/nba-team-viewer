package com.example.nbateamviewer

import com.example.nbateamviewer.data.NBAData
import com.example.nbateamviewer.data.Player
import com.example.nbateamviewer.data.Team
import com.example.nbateamviewer.network.NBAApiService
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class NBAApiServiceUnitTest {

    private lateinit var apiService: NBAApiService

    private lateinit var nbaData: NBAData

    private val teams = listOf(Team("Los Angeles Lakers", 18, 0, listOf(Player("Kobe", 2408, "Bryant", 24, "SG")), 82))

    @Before
    fun setup() {
        nbaData = NBAData()
        nbaData.addAll(teams)
        apiService = mock {
            on{getNBAData()} doReturn GlobalScope.async { nbaData }
        }
    }


    @ExperimentalCoroutinesApi
    @Test
    fun test() {
        assertTrue(apiService.getNBAData().getCompleted() == teams)
    }
}