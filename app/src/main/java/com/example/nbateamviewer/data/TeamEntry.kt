package com.example.nbateamviewer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team")
data class TeamEntry (
    @ColumnInfo(name = "full_name")
    val fullName: String,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "losses")
    val losses: Int,
    @ColumnInfo(name = "wins")
    val wins: Int
)