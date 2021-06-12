package com.example.edressing.Connection.repository

import com.example.edressing.Connection.entity.User
import com.example.edressing.Connection.local.DataBaseDao
import com.example.edressing.Connection.local.models.UserLocal
import com.example.edressing.Connection.local.models.toData
import com.example.edressing.Connection.local.models.toEntity

class UserRepository (
    private val databaseDao: DataBaseDao
){

    suspend fun createUser(user : User){
        databaseDao.insert(user.toData())
    }

    fun getUser(email: String) : User? {
        val userLocal : UserLocal? = databaseDao.findByName(email)
        return userLocal?.toEntity()
    }
}
