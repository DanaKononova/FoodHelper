package com.example.foodhelper.ui.nutritionPage

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodhelper.databinding.RvNutritionListBinding
import com.example.foodhelper.databinding.RvRecipeListBinding
import com.example.foodhelper.domain.models.InstructionsData
import com.example.foodhelper.domain.models.NutrientsData
import com.example.foodhelper.ui.recipePage.RecipeInnerAdapter

class NutritionViewHolder (
    private val binding: RvNutritionListBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: NutrientsData) {
        binding.nutrName.text = item.name
        binding.amount.text = item.amount
        binding.percent.text = "${item.percentOfDailyNeeds}%"
    }
}