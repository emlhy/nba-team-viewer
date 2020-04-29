package com.example.nbateamviewer.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nbateamviewer.data.PlayerEntry
import com.example.nbateamviewer.data.TeamEntry

@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(playerEntry: PlayerEntry)

    @Query("select * from player where team_id = :teamId")
    fun getPlayers(teamId: Int): LiveData<List<PlayerEntry>>
}