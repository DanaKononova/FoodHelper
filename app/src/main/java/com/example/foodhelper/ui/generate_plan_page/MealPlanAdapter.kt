package com.example.foodhelper.ui.generate_plan_page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.food.FoodData
import com.example.domain.models.generate_template.GenerateMealsData
import com.example.domain.models.generate_template.GenerateTemplateData
import com.example.foodhelper.databinding.RvMainFoodListBinding
import com.example.foodhelper.databinding.RvMealPlanListBinding
import com.example.foodhelper.ui.main_page.FoodViewHolder

class MealPlanAdapter (
    private val itemClick: (String, String, String) -> Unit
): RecyclerView.Adapter<MealPlanViewHolder>() {

    private val mealPlanList = mutableListOf<GenerateMealsData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealPlanViewHolder {
        val binding =
            RvMealPlanListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealPlanViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: MealPlanViewHolder, position: Int) {
        holder.onBind(mealPlanList[position])
    }

    override fun getItemCount(): Int = mealPlanList.size

    fun setFood(list: List<GenerateMealsData>) {
        mealPlanList.clear()
        mealPlanList.addAll(list)
        notifyDataSetChanged()
    }
}