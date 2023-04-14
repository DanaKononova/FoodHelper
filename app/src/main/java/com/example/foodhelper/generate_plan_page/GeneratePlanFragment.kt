package com.example.foodhelper.generate_plan_page

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.ViewModelFactory
import com.example.domain.models.generate_template.GenerateMealsData
import com.example.foodhelper.R
import com.example.foodhelper.databinding.FragmentGeneratePlanBinding
import com.example.foodhelper.FoodApp
import javax.inject.Inject

class GeneratePlanFragment : Fragment() {

    private var _binding: FragmentGeneratePlanBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: GeneratePlanViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        (activity?.applicationContext as FoodApp).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneratePlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val timeFrame = "week"
        var plan = ""
        binding.planNameEdit.addTextChangedListener {
            plan = it.toString()
        }

        val days = resources.getStringArray(R.array.days)
        var day = ""
        val daysSpinner = binding.daysSpinner
        daysSpinner.adapter = activity?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_dropdown_item,
                days
            )
        }
        daysSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                day = days[position]
                viewModel.getDayTemplate(day)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                if (day == "") day = days[0]
            }
        }

        var targetCalories = ""
        binding.targetCaloriesEdit.addTextChangedListener {
            targetCalories = it.toString()
        }

        val selectedDiet = binding.selectedDietTv
        binding.dietBt.setOnClickListener {
            val diets = resources.getStringArray(R.array.diets)
            var checkedItem = 0

            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Выберите элемент")
            builder.setSingleChoiceItems(diets, checkedItem) { _, which ->
                checkedItem = which
            }

            builder.setPositiveButton("OK") { _, _ ->
                selectedDiet.text = diets[checkedItem] // получить выбранный элемент
            }

            builder.setNegativeButton("Отмена") { _, _ ->
            }

            val dialog = builder.create()
            dialog.show()
        }

        var exclude = ""
        binding.excludeEdit.addTextChangedListener {
            exclude = it.toString()
        }

        val itemClick: (String, String, String) -> Unit = { id, image, name ->
            val action = GeneratePlanFragmentDirections.actionGeneratePlanFragmentToRecipeFragment(id, image, name)
            findNavController().navigate(action)
        }

        val adapter = MealPlanAdapter(itemClick)
        val recycler = binding.rvMealPlanList
        recycler.adapter = adapter
        recycler.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        val mealsList = mutableListOf<GenerateMealsData>()

        viewModel.generateTemplateLiveData.observe(viewLifecycleOwner) {
            mealsList.clear()
            mealsList.addAll(it.meals)
            adapter.setFood(mealsList)
            binding.caloriesAmount.text = it.nutrients.calories.toString()
            binding.carbohydratesAmount.text = it.nutrients.carbohydrates.toString()
            binding.fatAmount.text =  it.nutrients.fat.toString()
            binding.proteinAmount.text = it.nutrients.protein.toString()
            binding.caloriesTv.isVisible = true
            binding.carbohydratesTv.isVisible = true
            binding.fatTv.isVisible = true
            binding.proteinTv.isVisible = true
            binding.mealPlanNutrientsTv.isVisible = true
        }

        binding.getPlanBt.setOnClickListener {
            viewModel.generateTemplate(timeFrame, targetCalories, selectedDiet.text.toString(), exclude, day)
            viewModel.setToken("3d56490658e6406590fe5079373f64fe")
        }
        binding.addPlanBt.setOnClickListener{
            if (plan != "") viewModel.addPlan(plan)
            else Toast.makeText(requireContext(), "Enter plan name", Toast.LENGTH_LONG).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}