package com.example.nbateamviewer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nbateamviewer.R
import com.example.nbateamviewer.adapter.TeamAdapter
import com.example.nbateamviewer.data.TeamEntry
import com.example.nbateamviewer.db.NBADatabase
import com.example.nbateamviewer.db.TeamDao
import com.example.nbateamviewer.network.ConnectivityInterceptorImpl
import com.example.nbateamviewer.network.NBAApiService
import com.example.nbateamviewer.network.NBADataSource
import com.example.nbateamviewer.network.NBADataSourceImpl
import com.example.nbateamviewer.repository.NBARepositoryImpl
import com.example.nbateamviewer.viewmodel.TeamViewModel
import com.example.nbateamviewer.viewmodel.TeamViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header_team.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ScopedActivity(), TeamAdapter.OnItemClickListener {

    private lateinit var viewModelFactory: TeamViewModelFactory
    private lateinit var viewModel: TeamViewModel
    private lateinit var teamAdapter: TeamAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var nameAscending = true
    private var winsAscending = true
    private var lossesAscending = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService = NBAApiService(ConnectivityInterceptorImpl(this))
        val nbaDataSource = NBADataSourceImpl(apiService)
        val teamDao = NBADatabase(this).teamDao()
        val playerDao = NBADatabase(this).playerDao()
        val nbaRepository = NBARepositoryImpl(teamDao, playerDao, nbaDataSource)
        viewModelFactory = TeamViewModelFactory(nbaRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(TeamViewModel::class.java)

        bindUI()

    }

    private fun bindUI() = launch{
        val nbaData = viewModel.teams.await()
        teamAdapter = TeamAdapter(this@MainActivity)
        linearLayoutManager = LinearLayoutManager(this@MainActivity)
        teamRecyclerView.layoutManager = linearLayoutManager

        nbaData.observe(this@MainActivity, Observer {
            if (it == null) return@Observer

            val sortedList = it.sortedWith(compareBy({it.fullName}))
            teamAdapter.setTeamList(sortedList)
            teamRecyclerView.adapter = teamAdapter
        })

        progressBar.visibility = View.GONE

        teamNameText.setOnClickListener {
            if (nameAscending)
                teamAdapter.setTeamList(teamAdapter.getTeamList().sortedWith(compareByDescending { it.fullName }))
            else
                teamAdapter.setTeamList(teamAdapter.getTeamList().sortedWith(compareBy { it.fullName }))
            teamAdapter.notifyDataSetChanged()
            nameAscending = !nameAscending
            winsAscending = true
            lossesAscending = true
        }
        winsText.setOnClickListener {
            if (winsAscending)
                teamAdapter.setTeamList(teamAdapter.getTeamList().sortedWith(compareByDescending<TeamEntry> { it.wins }.thenBy { it.losses }.thenBy { it.fullName }))
            else
                teamAdapter.setTeamList(teamAdapter.getTeamList().sortedWith(compareBy<TeamEntry> { it.wins }.thenByDescending { it.losses }.thenBy { it.fullName }))
            teamAdapter.notifyDataSetChanged()
            nameAscending = false
            winsAscending = !winsAscending
            lossesAscending = true
        }
        lossesText.setOnClickListener {
            if (lossesAscending)
                teamAdapter.setTeamList(teamAdapter.getTeamList().sortedWith(compareByDescending<TeamEntry> { it.losses }.thenBy { it.wins }.thenBy { it.fullName }))
            else
                teamAdapter.setTeamList(teamAdapter.getTeamList().sortedWith(compareBy<TeamEntry> { it.losses }.thenByDescending { it.wins }.thenBy { it.fullName }))
            teamAdapter.notifyDataSetChanged()
            nameAscending = false
            winsAscending = true
            lossesAscending = !lossesAscending
        }
    }

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }
}
