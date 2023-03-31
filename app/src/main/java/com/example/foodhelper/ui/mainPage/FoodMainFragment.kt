package com.example.foodhelper.ui.mainPage

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodhelper.R
import com.example.foodhelper.data.di.ViewModelFactory
import com.example.foodhelper.databinding.FragmentMainBinding
import com.example.foodhelper.ui.FoodApp
import javax.inject.Inject

class FoodMainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: FoodViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        (activity?.applicationContext as FoodApp).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler = binding.rvFoodList
        var mealTv = binding.mealTv
        val searchBt = binding.searchBt

        val meals = resources.getStringArray(R.array.meals)
        val textMeals = arrayOf(
            getString(R.string.breakfastTv),
            getString(R.string.brunchTv),
            getString(R.string.lunchTv),
            getString(R.string.dinnerTv)
        )
        val spinner = binding.mealSpinner
        var query = meals[0]

        if (spinner != null) {
            spinner.adapter = this.context?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_dropdown_item,
                    meals
                )
            }
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (parent?.getChildAt(0) != null) {
                        val selectedView = parent.getChildAt(0) as TextView
                        selectedView.setTextColor(Color.WHITE)
                    }
                    query = meals[position]
                    mealTv.text = textMeals[position]
                    viewModel.getFoodList(query)
                    viewModel.setToken("3d56490658e6406590fe5079373f64fe")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    query = meals[0]
                    mealTv.text = textMeals[0]
                    if (parent?.getChildAt(0) != null) {
                        val selectedView = parent.getChildAt(0) as TextView
                        selectedView.setTextColor(Color.WHITE)
                    }
                }
            }
        }

        val itemClick: (String, String, String) -> Unit = { id, image, name ->
            val action = FoodMainFragmentDirections.actionMainFragmentToRecipeFragment(id, image, name)
            val navOptions = NavOptions.Builder()
                .setEnterAnim(androidx.transition.R.anim.abc_popup_enter)
                .setExitAnim(androidx.transition.R.anim.abc_popup_exit)
                .build()
            findNavController().navigate(action, navOptions)
        }

        val adapter = FoodAdapter(itemClick)
        recycler.adapter = adapter
        recycler.layoutManager =
            GridLayoutManager(this.context, 2)

        viewModel.foodLiveData.observe(viewLifecycleOwner) {
            adapter.setFood(it)
        }

        viewModel.getFoodList(query)
        viewModel.setToken("3d56490658e6406590fe5079373f64fe")

        searchBt.setOnClickListener {
            val action = FoodMainFragmentDirections.actionMainFragmentToSearchFoodFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}