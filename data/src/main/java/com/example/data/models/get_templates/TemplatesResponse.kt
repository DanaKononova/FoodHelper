package com.example.data.models.get_templates

import com.google.gson.annotations.SerializedName

data class TemplatesResponse (
    @SerializedName("templates") val templates: List<PlansResponse>? = null
)