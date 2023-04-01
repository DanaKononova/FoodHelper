package com.example.foodhelper.ui.nutritionPage

import androidx.recyclerview.widget.RecyclerView
import com.example.foodhelper.databinding.RvNutritionListBinding
import com.example.domain.models.NutrientsData

class NutritionViewHolder (
    private val binding: RvNutritionListBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: NutrientsData) {
        binding.nutrName.text = item.name
        binding.amount.text = item.amount
        binding.percent.text = "${item.percentOfDailyNeeds}%"
    }
}