package com.iua.app.domain.repository

import com.iua.app.data.remote.dto.UserDTO
import com.iua.app.domain.model.UserModel

interface UserRepository {

    suspend fun registerUser(userDTO: UserDTO): Boolean

    suspend fun getUserByEmailAndPassword(email: String, password: String): UserModel?
}