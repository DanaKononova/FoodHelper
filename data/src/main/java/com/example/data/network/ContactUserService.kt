package com.example.data.network

import com.example.data.models.user.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ContactUserService {
    @POST("users/connect")
    suspend fun getUser(
        @Body user: User,
        @Query("apiKey") token: String
    ): UserResponse
}




