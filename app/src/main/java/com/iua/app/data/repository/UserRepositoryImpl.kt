package com.iua.app.data.repository

import com.iua.app.data.remote.api.UserApi
import com.iua.app.data.remote.dto.UserRequestDTO
import com.iua.app.domain.model.UserModel
import com.iua.app.domain.model.toUserModel
import com.iua.app.domain.repository.UserRepository
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
            null // Manejo básico de errores
        }
    }

    override suspend fun registerUser(userRequestDTO: UserRequestDTO): UserModel? {
        return try {
            val response = userApi.registerUser(userRequestDTO)
            if (response.isSuccessful) {
                response.body()?.toUserModel() // Convierte UserResponseDTO a UserModel
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

//    override suspend fun registerUser(userRequestDTO: UserRequestDTO): Boolean {
//        return try {
//            val response = userApi.registerUser(userRequestDTO)
//            response.isSuccessful
//        } catch (e: Exception) {
//            false // Manejo de errores
//        }
//    }

    override suspend fun updateUserField(userId: String, field: String, value: String): UserModel? {
        return try {
            val updateRequest = mapOf(field to value)
            val response = userApi.updateUserField(userId, updateRequest)
            if (response.isSuccessful) {
                val updatedUser = response.body()?.toUserModel()
                updatedUser
            } else null
        } catch (e: Exception) {
            null // Manejo básico de errores
        }
    }

    override suspend fun deleteUser(userId: String): Result<Unit> {
        return try {
            val response = userApi.deleteUser(userId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to delete user: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}