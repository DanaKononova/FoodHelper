package com.example.foodhelper.nutrition_page

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.core.ViewModelFactory
import com.example.foodhelper.databinding.FragmentNutritionBinding
import com.example.domain.models.nutrients.NutrientsData
import com.example.foodhelper.FoodApp
import javax.inject.Inject

class NutritionFragment : Fragment() {

    private val args: NutritionFragmentArgs by navArgs()
    private var _binding: FragmentNutritionBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: NutritionViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        (activity?.applicationContext as FoodApp).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNutritionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recipeId = args.foodId
        val nutritionList = mutableListOf<NutrientsData>()
        val recycler = binding.rvNutritionList

        val adapter = NutritionAdapter()
        recycler.adapter = adapter
        recycler.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        binding.RecipeNameNutr.text = args.recipeName
        Glide
            .with(view)
            .load(args.image)
            .into(binding.recipeImgNutr)

        viewModel.nutrientsLiveData.observe(viewLifecycleOwner) {
            nutritionList.clear()
            nutritionList.addAll(it)
            adapter.setInstructions(it)
        }

        viewModel.getNutrientsList(recipeId)
        viewModel.setToken("3d56490658e6406590fe5079373f64fe")

        binding.nutrUrlTv.setOnClickListener {
            val address = Uri.parse(binding.nutrUrlTv.text.toString())
            val openLinkIntent = Intent(Intent.ACTION_VIEW, address)
            this.startActivity(openLinkIntent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}