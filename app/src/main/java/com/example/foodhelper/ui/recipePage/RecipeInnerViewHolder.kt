package com.example.foodhelper.ui.recipePage

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodhelper.databinding.RvEquipmentIngredientListBinding
import com.example.foodhelper.databinding.RvRecipeListBinding
import com.example.foodhelper.domain.models.EquipmentIngredientData
import com.example.foodhelper.domain.models.InstructionsData

class RecipeInnerViewHolder  (
    private val binding: RvEquipmentIngredientListBinding,
    private val isIngredients: Boolean
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: EquipmentIngredientData) {
        binding.objectTv.text = item.name
        val imageUrl = if (isIngredients) "https://spoonacular.com/cdn/ingredients_100x100/" + item.image
        else "https://spoonacular.com/cdn/equipment_100x100/" + item.image
        Glide
            .with(itemView)
            .load(imageUrl)
            .into(binding.objectImg)
    }
}