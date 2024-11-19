package com.iua.app.ui.view_models

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iua.app.data.datastore.DataStoreKeys
import com.iua.app.data.datastore.dataStore
import com.iua.app.domain.model.UserModel
import com.iua.app.domain.usecase.profile.DeleteUserUseCase
import com.iua.app.domain.usecase.profile.UpdateUserFieldUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class PersonalDataViewModel @Inject constructor(
    private val updateUserFieldUseCase: UpdateUserFieldUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _user = MutableLiveData<UserModel?>()
    val user: MutableLiveData<UserModel?> = _user

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

//    private val _updateError = MutableLiveData<String?>()
//    val updateError: LiveData<String?> = _updateError // queda para maneajar errores de actualización
//
//    private val _isUpdateSuccessful = MutableLiveData<Boolean>()
//    val isUpdateSuccessful: LiveData<Boolean> = _isUpdateSuccessful // queda para maneajar actualización

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _user.postValue(loadUserFromDataStore(context))
        }
    }

    private suspend fun saveUserToDataStore(user: UserModel) {
        val userIdKey = DataStoreKeys.USER_ID
        val userEmailKey = DataStoreKeys.USER_EMAIL
        val userNameKey = DataStoreKeys.USER_NAME
        val userLastNameKey = DataStoreKeys.USER_LAST_NAME

        context.dataStore.edit { preferences ->
            preferences[userIdKey] = user.id
            preferences[userEmailKey] = user.email
            preferences[userNameKey] = user.firstName
            preferences[userLastNameKey] = user.lastName
        }
    }

    private suspend fun loadUserFromDataStore(context: Context): UserModel {
        val preferences = context.dataStore.data.first()
        val userId = preferences[DataStoreKeys.USER_ID] ?: ""
        val email = preferences[DataStoreKeys.USER_EMAIL] ?: ""
        val firstName = preferences[DataStoreKeys.USER_NAME] ?: ""
        val lastName = preferences[DataStoreKeys.USER_LAST_NAME] ?: ""
        return UserModel(userId, firstName, lastName, email, "")
    }


    fun updateField(field: String, value: String) {
        val currentUser = _user.value ?: return

        _isLoading.value = true
        // _updateError.value = null
        // _isUpdateSuccessful.value = false

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val updatedUser = updateUserFieldUseCase.execute(
                    userId = currentUser.id, field = field, value = value
                )

                withContext(Dispatchers.Main) {
                    if (updatedUser != null) {
                        _user.value = updatedUser
                        saveUserToDataStore(updatedUser) // Guardar en DataStore
                        //_isUpdateSuccessful.value = true
                    } else {
                        throw Exception("Failed to update field")
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    // _updateError.value = e.message
                    _isLoading.value = false
                }
            } finally {
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                }
            }
        }
    }

    sealed class DeleteAccountState {
        data object Idle : DeleteAccountState()
        data object Loading : DeleteAccountState()
        data object Success : DeleteAccountState()
        data class Error(val message: String) : DeleteAccountState()
    }

    private val _deleteAccountState = MutableStateFlow<DeleteAccountState>(DeleteAccountState.Idle)
    val deleteAccountState: StateFlow<DeleteAccountState> = _deleteAccountState

    fun deleteAccount(userId: String) {
        _deleteAccountState.value = DeleteAccountState.Loading
        viewModelScope.launch {

            val result = deleteUserUseCase(userId)
            _deleteAccountState.value = if (result.isSuccess) {
                DeleteAccountState.Success
            } else {
                DeleteAccountState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }

            context.dataStore.edit { preferences ->
                preferences.clear()
            }
        }
    }

}