package com.example.assignment.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.data.network.Resource
import com.example.assignment.data.repository.AuthRepository
import com.example.assignment.data.responses.LoginResponse
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
):ViewModel(){

    private val _loginResponse : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get()= _loginResponse

    fun login(
        staff_id: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = repository.login(staff_id,password)
    }
}