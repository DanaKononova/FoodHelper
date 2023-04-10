package com.example.foodhelper.search_page

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.ViewModelFactory
import com.example.foodhelper.databinding.FragmentSearchFoodBinding
import com.example.domain.models.food.FoodData
import com.example.foodhelper.FoodApp
import com.example.foodhelper.main_page.FoodAdapter
import javax.inject.Inject

class SearchFoodFragment : Fragment() {

    private var _binding: FragmentSearchFoodBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: SearchViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        (activity?.applicationContext as FoodApp).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler = binding.rvSearchList
        val editText = binding.searchText

        val itemClick: (String, String, String) -> Unit = { id, image, name ->
            val action = SearchFoodFragmentDirections.actionSearchFoodFragmentToRecipeFragment(id, image, name)
            findNavController().navigate(action)
        }

        val adapter = FoodAdapter(itemClick)
        recycler.adapter = adapter

        val foodList = mutableListOf<FoodData>()

        var query: String
        editText.addTextChangedListener { text ->
            query = text.toString()
            var food = if (query != ""){
                viewModel.getFoodList(query)
                foodList
            } else emptyList()
            adapter.setFood(food)
        }

        recycler.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        viewModel.searchLiveData.observe(viewLifecycleOwner) {
            foodList.clear()
            foodList.addAll(it)
            adapter.setFood(it)
        }

        viewModel.setToken("3d56490658e6406590fe5079373f64fe")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}