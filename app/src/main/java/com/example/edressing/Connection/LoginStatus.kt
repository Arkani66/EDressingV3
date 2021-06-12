package com.example.edressing.Connection

sealed class LoginStatus

data class LoginSuccess(val email:String) : LoginStatus()
object LoginError : LoginStatus()