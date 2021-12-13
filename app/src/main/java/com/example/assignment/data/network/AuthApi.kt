package com.example.assignment.data.network

import com.example.assignment.data.responses.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("staff_id") staff_id:String,
        @Field("password") password:String

    ): LoginResponse
}