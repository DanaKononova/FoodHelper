package com.example.foodhelper.ui.nutrition_page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodhelper.databinding.RvNutritionListBinding
import com.example.domain.models.nutrients.NutrientsData

class NutritionAdapter() : RecyclerView.Adapter<NutritionViewHolder>() {

    private val nutritionList = mutableListOf<NutrientsData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutritionViewHolder {
        val binding =
            RvNutritionListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NutritionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NutritionViewHolder, position: Int) {
        holder.onBind(nutritionList[position])
    }

    override fun getItemCount(): Int = nutritionList.size

    fun setInstructions(list: List<NutrientsData>) {
        nutritionList.clear()
        nutritionList.addAll(list)
        notifyDataSetChanged()
    }
}