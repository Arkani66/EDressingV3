package com.example.edressing.Connection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android4a.domain.usecase.CreateUserUseCase
import com.example.edressing.Connection.entity.User
import com.example.edressing.Connection.usecase.GetUserUseCase
import kotlinx.coroutines.*

class MainViewModel(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel(){
    val loginLiveData : MutableLiveData<LoginStatus> = MutableLiveData()

    fun onClickedLogin(emailUser: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user : User? = getUserUseCase.invoke(emailUser)
            val loginStatus =  if(user != null ){
                LoginSuccess(user.email)
            }else{
                LoginError
            }
            withContext(Dispatchers.Main){
                loginLiveData.value = loginStatus
            }
        }
    }
    fun onClickedLoginAccount(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            createUserUseCase.invoke(user)
        }
    }


}