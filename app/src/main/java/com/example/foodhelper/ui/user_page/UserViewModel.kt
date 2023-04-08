package com.example.foodhelper.ui.user_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Repository
import com.example.domain.models.generate_template.GenerateTemplateData
import com.example.domain.models.get_templates.TemplatesData
import com.example.domain.models.user.UserData
import com.example.foodhelper.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _userLiveData = MutableLiveData<UserData>()
    val userLiveData: LiveData<UserData> get() = _userLiveData

    private val _templatesLiveData = MutableLiveData<List<TemplatesData>>()
    val templatesLiveData: LiveData<List<TemplatesData>> get() = _templatesLiveData

    private val _generateTemplateLiveData = MutableLiveData<GenerateTemplateData>()
    val generateTemplateLiveData: LiveData<GenerateTemplateData> get() = _generateTemplateLiveData

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

    fun getUserInfo(
        username: String,
        firstName: String,
        lastName: String,
        email: String
    ) {
        viewModelScope.launch(handler) {
            _userLiveData.value =
                repository.getUserInfo(username, firstName, lastName, email)
        }
    }

    fun getTemplates(
        username: String,
        hash: String
    ) {
        viewModelScope.launch(handler) {
            _templatesLiveData.value =
                repository.getTemplates(username, hash)
        }
    }

    fun setToken(token: String) {
        repository.setToken(token)
    }
}