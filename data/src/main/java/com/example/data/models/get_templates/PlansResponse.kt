package com.example.data.models.get_templates

import com.google.gson.annotations.SerializedName

data class PlansResponse(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String? = null,
)