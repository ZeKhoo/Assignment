package com.example.assignment.data.repository

import com.example.assignment.data.network.AuthApi

class AuthRepository(
    private val api: AuthApi
): BaseRepository(){

   suspend fun login(
        staff_id:String,
        password:String
    ) = safeApiCall {
        api.login(staff_id,password)
    }
}