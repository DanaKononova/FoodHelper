package com.example.data.models.generate_template

import com.google.gson.annotations.SerializedName

data class GenerateDaysResponse(
    @SerializedName("monday") val monday: GenerateTemplateResponse? = null,
    @SerializedName("tuesday") val tuesday: GenerateTemplateResponse? = null,
    @SerializedName("wednesday") val wednesday: GenerateTemplateResponse? = null,
    @SerializedName("thursday") val thursday: GenerateTemplateResponse? = null,
    @SerializedName("friday") val friday: GenerateTemplateResponse? = null,
    @SerializedName("saturday") val saturday: GenerateTemplateResponse? = null,
    @SerializedName("sunday") val sunday: GenerateTemplateResponse? = null
)