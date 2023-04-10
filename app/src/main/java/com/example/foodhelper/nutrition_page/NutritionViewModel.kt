package com.example.foodhelper.nutrition_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodhelper.R
import com.example.domain.Repository
import com.example.domain.models.nutrients.NutrientsData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

class NutritionViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _nutrientsLiveData = MutableLiveData<List<NutrientsData>>()
    val nutrientsLiveData: LiveData<List<NutrientsData>> get() = _nutrientsLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _errorLiveData = MutableLiveData<Int>()
    val errorLiveData: LiveData<Int> get() = _errorLiveData

    private val _noInternetLiveData = MutableLiveData<Boolean>()
    val noInternetLiveData: LiveData<Boolean> get() = _noInternetLiveData

    private val handler = CoroutineExceptionHandler { _, throwable: Throwable ->
        viewModelScope.launch {
            _noInternetLiveData.value = true

            when (throwable) {
                is SocketTimeoutException -> {
                    _errorLiveData.value = R.string.socketTimeout
                }
                else -> _errorLiveData.value = R.string.exception
            }
        }
    }

    fun getNutrientsList(id: String) {
        viewModelScope.launch(handler) {
            _noInternetLiveData.value = false
            _nutrientsLiveData.value =
                repository.getNutrientsList(id, !(noInternetLiveData.value ?: false))
        }
    }

    fun setToken(token: String) {
        repository.setToken(token)
    }
}