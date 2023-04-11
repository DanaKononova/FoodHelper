package com.example.data.models.get_templates

import com.squareup.moshi.Json

data class PlansResponse(
    @Json(name = "id") val id: String? = null,
    @Json(name = "name") val name: String? = null,
)