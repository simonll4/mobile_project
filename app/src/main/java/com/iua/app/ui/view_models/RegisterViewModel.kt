package com.iua.app.ui.view_models

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iua.app.domain.model.Resource
import com.iua.app.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _firstName = MutableLiveData<String>()
    val firstName: LiveData<String> = _firstName

    private val _lastName = MutableLiveData<String>()
    val lastName: LiveData<String> = _lastName

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _repeatPassword = MutableLiveData<String>()
    val repeatPassword: LiveData<String> = _repeatPassword

    private val _checkBoxState = MutableLiveData(false)
    val checkBoxState: LiveData<Boolean> get() = _checkBoxState

    private val _registerEnable = MutableLiveData(false)
    val registerEnable: LiveData<Boolean> get() = _registerEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isRegisterSuccessful = MutableLiveData<Boolean>()
    val isRegisterSuccessful: LiveData<Boolean> = _isRegisterSuccessful

    fun onRegisterChanged(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        repeatPassword: String,
        checkBoxState: Boolean
    ) {
        _firstName.value = firstName
        _lastName.value = lastName
        _email.value = email
        _password.value = password
        _repeatPassword.value = repeatPassword
        _checkBoxState.value = checkBoxState

        _registerEnable.value =
            email.isValidEmail() && password.isValidPassword() && password == repeatPassword && checkBoxState
    }

    private fun String.isValidEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun String.isValidPassword(): Boolean = this.length >= 6


    fun registerUser() {
        viewModelScope.launch {
            _isLoading.value = true
            registerUseCase(
                firstName.value ?: "",
                lastName.value ?: "",
                email.value ?: "",
                password.value ?: ""
            ).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                    }

                    is Resource.Success -> {
                        _isRegisterSuccessful.value = true
                    }

                    is Resource.Error -> {
                        _isRegisterSuccessful.value = false
                    }
                }
                _isLoading.value = false
            }
        }
    }
}