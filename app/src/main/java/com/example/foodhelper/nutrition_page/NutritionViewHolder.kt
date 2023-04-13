package com.example.foodhelper.nutrition_page

import androidx.recyclerview.widget.RecyclerView
import com.example.foodhelper.databinding.RvNutritionListBinding
import com.example.domain.models.nutrients.NutrientsData

class NutritionViewHolder (
    private val binding: RvNutritionListBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: NutrientsData) {
        binding.nutrName.text = item.title
//        binding.amount.text = item.amount.toString()
//        binding.percent.text = "${item.percentOfDailyNeeds}%"
    }
}