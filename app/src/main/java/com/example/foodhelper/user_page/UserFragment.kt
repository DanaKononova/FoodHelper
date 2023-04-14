package com.example.foodhelper.user_page

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.ViewModelFactory
import com.example.domain.models.generate_template.GenerateMealsData
import com.example.foodhelper.databinding.FragmentUserBinding
import com.example.foodhelper.FoodApp
import com.example.foodhelper.databinding.AlertDialogBinding
import com.example.foodhelper.generate_plan_page.GeneratePlanFragmentDirections
import com.example.foodhelper.generate_plan_page.MealPlanAdapter
import java.util.*
import javax.inject.Inject

class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: UserViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        (activity?.applicationContext as FoodApp).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val plans = mutableListOf<String>()
        var currentPlan = 0

        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        val planItemClick: (String) -> Unit = {name ->
            val builder = AlertDialog.Builder(requireContext())
            val dialogLayout = AlertDialogBinding.inflate(layoutInflater, null, false)
            builder.setView(dialogLayout.root)
            val alertDialog = builder.create()

            dialogLayout.editPlanName.hint = name
            var newName = name
            dialogLayout.changeBt.setOnClickListener {
                newName = dialogLayout.editPlanName.text.toString()
                viewModel.changePlanName(name, newName)
                viewModel.getPlans()
            }

            dialogLayout.setCurrentBt.setOnClickListener {
                currentPlan = plans.indexOf(newName)
                viewModel.getPlans()
            }

            dialogLayout.deletePlanBt.setOnClickListener {
                viewModel.deletePlan(newName)
                viewModel.getPlans()
            }
            alertDialog.show()
        }

        val mealItemClick: (String, String, String) -> Unit = { id, image, name ->
            val action = UserFragmentDirections.actionUserFragmentToRecipeFragment(id, image, name)
            findNavController().navigate(action)
        }

        val plansAdapter = PlansAdapter(planItemClick)
        val plansRecycler = binding.rvPlansList
        plansRecycler.adapter = plansAdapter
        plansRecycler.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        val mealsAdapter = MealPlanAdapter(mealItemClick)
        val mealsRecycler = binding.rvCurrentMeals
        mealsRecycler.adapter = mealsAdapter
        mealsRecycler.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        viewModel.plansLiveData.observe(viewLifecycleOwner){
            plans.clear()
            plans.addAll(it)
            plansAdapter.setPlans(it)
            binding.currentPlanNameTv.text = it[currentPlan]
            viewModel.getCurrentPlan(plans[currentPlan], dayOfWeek)
            viewModel.getCurrentNutrients(plans[currentPlan], dayOfWeek)
        }

        val mealsList = mutableListOf<GenerateMealsData>()

        viewModel.currentPlanLiveData.observe(viewLifecycleOwner){
            mealsList.addAll(it.map { dayPlan ->
                GenerateMealsData(dayPlan.id, dayPlan.title, dayPlan.readyInMinutes, dayPlan.servings, dayPlan.sourceUrl, dayPlan.imageType)
            })
            mealsAdapter.setFood(mealsList)
        }

        viewModel.currentNutrientsLiveData.observe(viewLifecycleOwner){
            binding.caloriesAmount.text = it.calories.toString()
            binding.carbohydratesAmount.text = it.carbohydrates.toString()
            binding.fatAmount.text = it.fat.toString()
            binding.proteinAmount.text = it.protein.toString()
        }

        viewModel.getPlans()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}