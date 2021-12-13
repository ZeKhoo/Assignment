package com.example.assignment.data.responses

data class User(
    val access_token: String?,
    val refresh_token: String?,
    val created_at: String,
    val staff_id: String,
    val updated_at: String
)