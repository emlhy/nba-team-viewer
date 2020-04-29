package com.example.nbateamviewer.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nbateamviewer.data.PlayerEntry
import com.example.nbateamviewer.data.TeamEntry

@Database(
    entities = [TeamEntry::class, PlayerEntry::class],
    version = 1
)

abstract class NBADatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao
    abstract fun playerDao(): PlayerDao

    companion object {
        @Volatile private var instance: NBADatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                NBADatabase::class.java, "nba.db")
                .build()
    }
}