package com.example.nbateamviewer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player")
data class PlayerEntry(
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "number")
    val number: Int,
    @ColumnInfo(name = "position")
    val position: String,
    @ColumnInfo(name = "team_id")
    val teamId: Int
)