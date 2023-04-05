package com.example.data.models.user

import com.google.gson.annotations.SerializedName

class UserResponse (
    @SerializedName("username") val username: String? = null,
    @SerializedName("spoonacularPassword") val spoonacularPassword: String? = null,
    @SerializedName("hash") val hash: String? = null
)