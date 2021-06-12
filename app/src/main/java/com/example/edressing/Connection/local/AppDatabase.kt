package com.example.edressing.Connection.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.edressing.Connection.local.models.UserLocal

@Database(entities = arrayOf(
    UserLocal::class
),  version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDao(): DataBaseDao
}