package com.iua.app.data.repository

import com.iua.app.data.remote.api.UserApi
import com.iua.app.data.remote.dto.UserDTO
import com.iua.app.domain.model.UserModel
import com.iua.app.domain.model.toUserModel
import com.iua.app.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {

    override suspend fun getUserByEmailAndPassword(email: String, password: String): UserModel? {
        return try {
            // Llamada al API
            val response = userApi.getUserByEmailAndPassword(email, password)

            // Si hay resultados, toma el primero; de lo contrario, retorna null
            response.firstOrNull()?.toUserModel()
        } catch (e: Exception) {
            null // Manejo básico de errores (puede extenderse con logs o excepciones personalizadas)
        }
    }

    override suspend fun registerUser(userDTO: UserDTO): Boolean {
        val response = userApi.registerUser(userDTO)
        return response.isSuccessful
    }
}
