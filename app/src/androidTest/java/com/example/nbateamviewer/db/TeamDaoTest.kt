package com.example.nbateamviewer.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nbateamviewer.data.TeamEntry
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class TeamDaoTest : DbTest() {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var teamDao: TeamDao

    private val teamEntry = TeamEntry("Los Angeles Lakers", 18, 0, 82)

    @Test
    fun test() {
        teamDao = db.teamDao()
        teamDao.upsert(teamEntry)
        val teams = teamDao.getTeams().blockingObserve()
        assertTrue(teams == listOf(teamEntry))
    }

    private fun <T> LiveData<T>.blockingObserve(): T? {
        var value: T? = null
        val latch = CountDownLatch(1)

        val observer = Observer<T> { t ->
            value = t
            latch.countDown()
        }

        observeForever(observer)

        latch.await(2, TimeUnit.SECONDS)
        return value
    }
}