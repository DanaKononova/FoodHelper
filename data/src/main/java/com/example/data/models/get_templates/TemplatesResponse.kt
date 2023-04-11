package com.example.data.models.get_templates

import com.squareup.moshi.Json

data class TemplatesResponse (
    @Json(name = "templates") val templates: List<PlansResponse>? = null
)