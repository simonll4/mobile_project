package com.iua.app.ui.view_models

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iua.app.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isLoginSuccessful = MutableLiveData<Boolean>()
    val isLoginSuccessful: LiveData<Boolean> = _isLoginSuccessful

    private val _loginError = MutableLiveData<String?>()
    val loginError: LiveData<String?> = _loginError

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = email.isValidEmail() && password.isValidPassword()
    }

    private fun String.isValidEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun String.isValidPassword(): Boolean = this.length >= 6

    fun onButtonSelected(userViewModel: UserViewModel) {
        val email = _email.value.orEmpty()
        val password = _password.value.orEmpty()

        _isLoading.value = true // Mostrar indicador de carga
        _loginError.value = null // Limpiar errores previos
        _isLoginSuccessful.value = false // Resetear estado de éxito

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val user = loginUseCase.execute(email, password)
                if (user != null) {
                    withContext(Dispatchers.Main) {
                        userViewModel.login(user) // Guardar usuario en el modelo global
                        _isLoading.value = false
                        _isLoginSuccessful.value = true // Marcar como exitoso
                    }
                } else {
                    throw Exception("Invalid credentials")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _isLoading.value = false // Ocultar indicador de carga
                    _loginError.value = e.message // Mostrar mensaje de error
                    _isLoginSuccessful.value = false // Mantener en no exitoso
                }
            }
        }
    }

}


//@HiltViewModel
//class LoginViewModel @Inject constructor(
//    private val loginUseCase: LoginUseCase
//) : ViewModel() {
//
//    private val _email = MutableLiveData<String>()
//    val email: LiveData<String> = _email
//
//    private val _password = MutableLiveData<String>()
//    val password: LiveData<String> = _password
//
//    private val _loginEnable = MutableLiveData<Boolean>()
//    val loginEnable: LiveData<Boolean> = _loginEnable
//
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//    private val _loginError = MutableLiveData<String?>()
//    val loginError: LiveData<String?> = _loginError
//
//    fun onLoginChanged(email: String, password: String) {
//        _email.value = email
//        _password.value = password
//        _loginEnable.value = email.isValidEmail() && password.isValidPassword()
//    }
//
//    private fun String.isValidEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()
//
//    private fun String.isValidPassword(): Boolean = this.length >= 6
//
//    fun onButtonSelected(userViewModel: UserViewModel) {
//        _isLoading.value = true
//        _loginError.value = null // Limpiar errores previos
//
//        val email = email.value.orEmpty()
//        val password = password.value.orEmpty()
//
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val user = loginUseCase.execute(email, password)
//                if (user != null) {
//                    withContext(Dispatchers.Main) {
//                        userViewModel.login(user) // Guardar el usuario en UserViewModel
//                        _isLoading.value = false
//                    }
//                } else {
//                    throw Exception("Invalid credentials")
//                }
//            } catch (e: Exception) {
//                withContext(Dispatchers.Main) {
//                    _isLoading.value = false
//                    _loginError.value = e.message
//                }
//            }
//        }
//    }
//}


//@HiltViewModel
//class LoginViewModel @Inject constructor(
//    private val loginUseCase: LoginUseCase
//) : ViewModel() {
//
//    private val _email = MutableLiveData<String>()
//    val email: LiveData<String> = _email
//
//    private val _password = MutableLiveData<String>()
//    val password: LiveData<String> = _password
//
//    private val _loginEnable = MutableLiveData<Boolean>()
//    val loginEnable: LiveData<Boolean> = _loginEnable
//
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//    private val _isLoginSuccessful = MutableLiveData<Boolean>()
//    val isLoginSuccessful: LiveData<Boolean> = _isLoginSuccessful
//
//    fun onLoginChanged(email: String, password: String) {
//        _email.value = email
//        _password.value = password
//        _loginEnable.value = email.isValidEmail() && password.isValidPassword()
//    }
//
//    private fun String.isValidEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()
//
//    private fun String.isValidPassword(): Boolean = this.length >= 6
//
////    suspend fun onButtonSelected() {
////        _isLoading.value = true
////        delay(2000) // Simulate a network request
////        _isLoading.value = false
////        _isLoginSuccessful.value = true
////    }
//
//    fun onButtonSelected() {
//        _isLoading.value = true
//        val email = email.value ?: ""
//
//        CoroutineScope(Dispatchers.IO).launch {
//            loginUseCase.execute(email, password.value ?: "").collect {
//                withContext(Dispatchers.Main) {
//                    _isLoading.value = false
//                    _isLoginSuccessful.value = it
//                }
//            }
//        }
//    }
//
//
//}