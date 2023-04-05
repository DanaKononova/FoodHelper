package com.example.foodhelper.ui.user_page

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.core.ViewModelFactory
import com.example.foodhelper.databinding.FragmentUserBinding
import com.example.foodhelper.ui.FoodApp
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
        viewModel.userLiveData.observe(viewLifecycleOwner){
            binding.spoonacularPassword.text = it.spoonacularPassword
            binding.username.text = it.username
            binding.hash.text = it.hash
        }

        viewModel.generateTemplate("day", "2500", "vegeterian", "")
        viewModel.setToken("3d56490658e6406590fe5079373f64fe")

//        viewModel.getTemplates("dana888", "8b751e68e925d423af52131dd867b141fe803080")
//        viewModel.setToken("3d56490658e6406590fe5079373f64fe")

//        viewModel.getUserInfo("dana888", "Dana", "Kononova", "dana.kononova@mail.ru")
//        viewModel.setToken("3d56490658e6406590fe5079373f64fe")

      //  username":"dana888","spoonacularPassword":"biryanion97annattoextract","hash":"8b751e68e925d423af52131dd867b141fe803080"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}