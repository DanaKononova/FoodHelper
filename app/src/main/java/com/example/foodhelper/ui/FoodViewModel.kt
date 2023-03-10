package com.example.foodhelper.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodhelper.domain.Repository
import com.example.foodhelper.domain.models.FoodData
import com.example.foodhelper.domain.models.InstructionsData
import com.example.foodhelper.domain.models.NutrientsData
import kotlinx.coroutines.launch
import javax.inject.Inject

class FoodViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _foodLiveData = MutableLiveData<List<FoodData>>()
    val foodLiveData: LiveData<List<FoodData>> get() = _foodLiveData

    private val _nutrientsLiveData = MutableLiveData<List<NutrientsData>>()
    val nutrientsLiveData: LiveData<List<NutrientsData>> get() = _nutrientsLiveData

    private val _instructionsLiveData = MutableLiveData<List<List<InstructionsData>>>()
    val instructionsLiveData: LiveData<List<List<InstructionsData>>> get() = _instructionsLiveData

//    private val _loadingLiveData = MutableLiveData<Boolean>()
//    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData
//
//    private val _errorLiveData = MutableLiveData<Int>()
//    val errorLiveData: LiveData<Int> get() = _errorLiveData

//    private val handler = CoroutineExceptionHandler { _, throwable: Throwable ->
//        when (throwable) {
//            is SocketTimeoutException -> {
//                _errorLiveData.value = R.string.socketTimeout
//            }
//            else -> _errorLiveData.value = R.string.exception
//        }
//    }

    fun getFoodList(query: String) {
        viewModelScope.launch() {
            _foodLiveData.value = repository.getFoodList(query)
        }
    }

    fun getNutrientsList(id: String) {
        viewModelScope.launch() {
            _nutrientsLiveData.value = repository.getNutrientsList(id)
        }
    }

    fun getInstructionsList(id: String) {
        viewModelScope.launch() {
            _instructionsLiveData.value = repository.getInstructionsList(id)
        }
    }

    fun setToken(token: String) {
        repository.setToken(token)
    }
}