package com.iua.app.ui.view_models

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iua.app.domain.usecase.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import android.content.Context
import androidx.datastore.preferences.core.edit
import com.iua.app.data.datastore.DataStoreKeys
import com.iua.app.data.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    @ApplicationContext private val context: Context
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

    private fun String.isValidEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun String.isValidPassword(): Boolean = this.length >= 6

    private suspend fun saveUserToDataStore(
        context: Context,
        userId: String,
        email: String,
        firstName: String,
        lastName: String
    ) {
        val userIdKey = DataStoreKeys.USER_ID
        val userEmailKey = DataStoreKeys.USER_EMAIL
        val userNameKey = DataStoreKeys.USER_NAME
        val userLastNameKey = DataStoreKeys.USER_LAST_NAME

        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.IS_USER_LOGGED_IN] = true
            preferences[userIdKey] = userId
            preferences[userEmailKey] = email
            preferences[userNameKey] = firstName
            preferences[userLastNameKey] = lastName
        }
    }

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = email.isValidEmail() && password.isValidPassword()
    }

    fun onButtonSelected() {
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
                        // Guardar usuario en DataStore
                        saveUserToDataStore(
                            context,
                            user.id,
                            user.email,
                            user.firstName,
                            user.lastName
                        )
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


