package com.example.foodhelper.user_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Repository
import com.example.domain.models.generate_template.GenerateTemplateData
import com.example.domain.models.get_templates.TemplatesData
import com.example.foodhelper.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _plansLiveData = MutableLiveData<List<String>>()
    val plansLiveData: LiveData<List<String>> get() = _plansLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _errorLiveData = MutableLiveData<Int>()
    val errorLiveData: LiveData<Int> get() = _errorLiveData

    private val handler = CoroutineExceptionHandler { _, throwable: Throwable ->
        viewModelScope.launch {
            when (throwable) {
                is SocketTimeoutException -> {
                    _errorLiveData.value = R.string.socketTimeout
                }
                else -> _errorLiveData.value = R.string.exception
            }
        }
    }

    fun getPlans() {
        viewModelScope.launch(handler) {
            _plansLiveData.value = repository.getPlans()
        }
    }

    fun setToken(token: String) {
        repository.setToken(token)
    }
}