package com.example.nbateamviewer.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbateamviewer.R
import com.example.nbateamviewer.adapter.PlayerAdapter
import com.example.nbateamviewer.data.PlayerEntry
import com.example.nbateamviewer.db.NBADatabase
import com.example.nbateamviewer.repository.PlayerRepositoryImpl
import com.example.nbateamviewer.viewmodel.PlayerViewModel
import com.example.nbateamviewer.viewmodel.PlayerViewModelFactory
import kotlinx.android.synthetic.main.activity_player.*
import kotlinx.android.synthetic.main.header_player.*
import kotlinx.coroutines.launch

class PlayerActivity : ScopedActivity() {
    private lateinit var viewModelFactory: PlayerViewModelFactory
    private lateinit var viewModel: PlayerViewModel
    private lateinit var playerAdapter: PlayerAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var name: String
    private var teamId = -1
    private var w = -1
    private var l = -1
    private var firstNameAscending = false
    private var lastNameAscending = false
    private var positionAscending = false
    private var numberAscending = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        val bundle: Bundle? = intent.extras
        teamId = bundle?.getInt("teamId")!!
        name = bundle.getString("teamName", "")
        w = bundle.getInt("wins")
        l = bundle.getInt("losses")

        val playerDao = NBADatabase(this).playerDao()
        val playerRepository = PlayerRepositoryImpl(playerDao)
        viewModelFactory = PlayerViewModelFactory(playerRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlayerViewModel::class.java)

        bindUI()

    }

    private fun bindUI() = launch{
        toolbar.title = name
        if (teamId != -1) {
            val nbaData = viewModel.players(teamId).await()
            playerAdapter = PlayerAdapter()
            wins.text = w.toString()
            losses.text = l.toString()
            linearLayoutManager = LinearLayoutManager(this@PlayerActivity)
            playerRecyclerView.layoutManager = linearLayoutManager

            nbaData.observe(this@PlayerActivity, Observer {
                if (it == null) return@Observer

                val sortedList = it.sortedWith(compareBy { it.number })
                playerAdapter.setPlayerList(sortedList)
                playerRecyclerView.adapter = playerAdapter
            })

            progressBar.visibility = View.GONE
        }

        numberText.setOnClickListener {
            if (numberAscending)
                playerAdapter.setPlayerList(playerAdapter.getPlayerList().sortedWith(
                    compareByDescending { it.number }))
            else
                playerAdapter.setPlayerList(playerAdapter.getPlayerList().sortedWith(
                    compareBy { it.number }))
            playerAdapter.notifyDataSetChanged()
            numberAscending = !numberAscending
            firstNameAscending = false
            lastNameAscending = false
            positionAscending = false
        }

        firstNameText.setOnClickListener {
            if (firstNameAscending)
                playerAdapter.setPlayerList(playerAdapter.getPlayerList().sortedWith(
                    compareByDescending<PlayerEntry> { it.firstName }.thenByDescending {it.lastName}))
            else
                playerAdapter.setPlayerList(playerAdapter.getPlayerList().sortedWith(
                    compareBy<PlayerEntry> { it.firstName }.thenBy {it.lastName}))
            playerAdapter.notifyDataSetChanged()
            numberAscending = false
            firstNameAscending = !firstNameAscending
            lastNameAscending = false
            positionAscending = false
        }

        lastNameText.setOnClickListener {
            if (lastNameAscending)
                playerAdapter.setPlayerList(playerAdapter.getPlayerList().sortedWith(
                    compareByDescending<PlayerEntry> { it.lastName }.thenByDescending {it.firstName}))
            else
                playerAdapter.setPlayerList(playerAdapter.getPlayerList().sortedWith(
                    compareBy<PlayerEntry> { it.lastName }.thenBy {it.firstName}))
            playerAdapter.notifyDataSetChanged()
            numberAscending = false
            firstNameAscending = false
            lastNameAscending = !lastNameAscending
            positionAscending = false
        }

        positionText.setOnClickListener {
            if (positionAscending)
                playerAdapter.setPlayerList(playerAdapter.getPlayerList().sortedWith(
                    compareByDescending<PlayerEntry> { it.position }.thenBy {it.number}))
            else
                playerAdapter.setPlayerList(playerAdapter.getPlayerList().sortedWith(
                    compareBy<PlayerEntry> { it.position }.thenBy {it.number}))
            playerAdapter.notifyDataSetChanged()
            numberAscending = false
            firstNameAscending = false
            lastNameAscending = false
            positionAscending = !positionAscending
        }
    }
}