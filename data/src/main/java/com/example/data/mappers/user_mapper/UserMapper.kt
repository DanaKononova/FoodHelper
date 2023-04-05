package com.example.data.mappers.user_mapper

import com.example.data.models.user.UserResponse
import com.example.domain.models.user.UserData
import javax.inject.Inject

class UserMapper @Inject constructor() {
    operator fun invoke(userResponse: UserResponse) = with(userResponse) {
        UserData(
            username = userResponse.username ?: "",
            spoonacularPassword = userResponse.spoonacularPassword ?: "",
            hash = userResponse.hash ?: ""
        )
    }
}