package com.example.nbateamviewer.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nbateamviewer.data.TeamEntry

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(teamEntry: TeamEntry)

    @Query("select * from team")
    fun getTeams(): LiveData<List<TeamEntry>>
}