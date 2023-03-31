package com.example.foodhelper.ui.recipePage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.foodhelper.data.di.ViewModelFactory
import com.example.foodhelper.databinding.FragmentRecipeBinding
import com.example.foodhelper.domain.models.InstructionsData
import com.example.foodhelper.ui.FoodApp
import com.example.foodhelper.ui.mainPage.FoodMainFragmentDirections
import javax.inject.Inject

class RecipeFragment : Fragment() {

    private val args: RecipeFragmentArgs by navArgs()
    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: RecipeViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        (activity?.applicationContext as FoodApp).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recipeId = args.foodId
        val instructionList = mutableListOf<InstructionsData>()
        val instructionRecycler = binding.rvRecipeList

        val adapter = RecipeAdapter()
        instructionRecycler.adapter = adapter
        instructionRecycler.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        binding.RecipeName.text = args.recipeName
        Glide
            .with(view)
            .load(args.image)
            .into(binding.recipeImg)

        binding.nutritionBt.setOnClickListener{
            val action = RecipeFragmentDirections.actionRecipeFragmentToNutritionFragment(args.foodId, args.image, args.recipeName)
            val navOptions = NavOptions.Builder()
                .setEnterAnim(androidx.transition.R.anim.abc_popup_enter)
                .setExitAnim(androidx.transition.R.anim.abc_popup_exit)
                .build()
            findNavController().navigate(action, navOptions)
        }

        viewModel.instructionsLiveData.observe(viewLifecycleOwner){
            instructionList.clear()
            instructionList.addAll(it)
            adapter.setInstructions(it)
        }

        viewModel.getInstructionsList(recipeId)
        viewModel.setToken("3d56490658e6406590fe5079373f64fe")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}