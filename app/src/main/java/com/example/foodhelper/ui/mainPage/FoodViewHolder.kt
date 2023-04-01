package com.example.foodhelper.ui.mainPage

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodhelper.databinding.RvMainFoodListBinding
import com.example.domain.models.FoodData

class FoodViewHolder(
    private val binding: RvMainFoodListBinding,
    private val itemClick: (String, String, String) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: FoodData) {
        binding.foodTv.text = item.title

        Glide
            .with(itemView)
            .load(item.image)
            .into(binding.foodImg)

        itemView.setOnClickListener {
            itemClick.invoke(item.id.toString(), item.image, item.title)
        }
    }
}