package com.example.foodhelper.user_page

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.core.ViewModelFactory
import com.example.foodhelper.databinding.FragmentUserBinding
import com.example.foodhelper.FoodApp
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
        viewModel.plansLiveData.observe(viewLifecycleOwner){
            it.map { plan ->
                println(plan)
            }
        }

        viewModel.getPlans()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}