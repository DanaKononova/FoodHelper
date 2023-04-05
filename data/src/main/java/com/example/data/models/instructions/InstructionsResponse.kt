package com.example.data.models.instructions

import com.google.gson.annotations.SerializedName

data class InstructionsResponse(
    @SerializedName("steps") val steps: List<InstructionStepsResponse>? = null
)