package com.example.foodhelper.ui.generate_plan_page

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.core.ViewModelFactory
import com.example.foodhelper.databinding.FragmentGeneratePlanBinding
import com.example.foodhelper.databinding.FragmentUserBinding
import com.example.foodhelper.ui.FoodApp
import com.example.foodhelper.ui.user_page.UserViewModel
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
    ): View? {
        _binding = FragmentGeneratePlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.generateTemplateLiveData.observe(viewLifecycleOwner){

        }

        viewModel.generateTemplate("day", "2500", "vegeterian", "")
        viewModel.setToken("3d56490658e6406590fe5079373f64fe")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}