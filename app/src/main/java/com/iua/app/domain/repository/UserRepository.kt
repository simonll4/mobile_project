package com.iua.app.domain.repository

import com.iua.app.data.remote.dto.UserRequestDTO
import com.iua.app.domain.model.UserModel

interface UserRepository {

    suspend fun registerUser(userRequestDTO: UserRequestDTO): Boolean

    suspend fun getUserByEmailAndPassword(email: String, password: String): UserModel?

    suspend fun updateUserField(userId: String, field: String, value: String): UserModel?

    suspend fun deleteUser(userId: String): Result<Unit>
}