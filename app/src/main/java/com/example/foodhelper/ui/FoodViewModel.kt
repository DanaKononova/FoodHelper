package com.example.foodhelper.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodhelper.R
import com.example.foodhelper.domain.Repository
import com.example.foodhelper.domain.models.FoodData
import com.example.foodhelper.domain.models.InstructionsData
import com.example.foodhelper.domain.models.NutrientsData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

class FoodViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _foodLiveData = MutableLiveData<List<FoodData>>()
    val foodLiveData: LiveData<List<FoodData>> get() = _foodLiveData

    private val _nutrientsLiveData = MutableLiveData<List<NutrientsData>>()
    val nutrientsLiveData: LiveData<List<NutrientsData>> get() = _nutrientsLiveData

    private val _instructionsLiveData = MutableLiveData<List<InstructionsData>>()
    val instructionsLiveData: LiveData<List<InstructionsData>> get() = _instructionsLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _errorLiveData = MutableLiveData<Int>()
    val errorLiveData: LiveData<Int> get() = _errorLiveData

    private val _noInternetLiveData = MutableLiveData<Boolean>()
    val noInternetLiveData: LiveData<Boolean> get() = _noInternetLiveData

    private val handler = CoroutineExceptionHandler { _, throwable: Throwable ->
        viewModelScope.launch {
            _noInternetLiveData.value = true
            _foodLiveData.value = repository.getFoodList("", !(noInternetLiveData.value ?: false))
            _nutrientsLiveData.value = repository.getNutrientsList("", !(noInternetLiveData.value ?: false))
            _instructionsLiveData.value = repository.getInstructionsList("", !(noInternetLiveData.value ?: false))

            when (throwable) {
                is SocketTimeoutException -> {
                    _errorLiveData.value = R.string.socketTimeout
                }
                else -> _errorLiveData.value = R.string.exception
            }
        }
    }

    fun getFoodList(query: String) {
        viewModelScope.launch(handler) {
            _noInternetLiveData.value = false
            _foodLiveData.value = repository.getFoodList(query, !(noInternetLiveData.value ?: false))
        }
    }

    fun getNutrientsList(id: String) {
        viewModelScope.launch(handler) {
            _noInternetLiveData.value = false
            _nutrientsLiveData.value = repository.getNutrientsList(id, !(noInternetLiveData.value ?: false))
        }
    }

    fun getInstructionsList(id: String) {
        viewModelScope.launch(handler) {
            _noInternetLiveData.value = false
            _instructionsLiveData.value = repository.getInstructionsList(id, !(noInternetLiveData.value ?: false))
        }
    }

    fun setToken(token: String) {
        repository.setToken(token)
    }
}