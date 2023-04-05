package com.example.data.mappers.get_templates_mapper

import com.example.data.models.get_templates.PlansResponse
import com.example.domain.models.get_templates.TemplatesData
import javax.inject.Inject

class TemplatesMapper @Inject constructor() {
    operator fun invoke(plansResponse: PlansResponse) = with(plansResponse) {
        TemplatesData(
            id = plansResponse.id ?: "",
            name = plansResponse.name ?: ""
        )
    }
}