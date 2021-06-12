package com.example.edressing.Connection.usecase

import com.example.edressing.Connection.entity.User
import com.example.edressing.Connection.repository.UserRepository

class GetUserUseCase ( private val userRepository: UserRepository){
    suspend fun invoke (email: String) : User? {
       return  userRepository.getUser(email)
    }

}