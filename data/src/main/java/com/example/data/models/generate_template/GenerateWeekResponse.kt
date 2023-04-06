package com.example.data.models.generate_template

import com.google.gson.annotations.SerializedName

data class GenerateWeekResponse (
    @SerializedName("week") val week: GenerateDaysResponse? = null,
)