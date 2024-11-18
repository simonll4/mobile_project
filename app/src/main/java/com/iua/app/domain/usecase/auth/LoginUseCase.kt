package com.iua.app.domain.usecase.auth

import com.iua.app.domain.model.UserModel
import com.iua.app.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(email: String, password: String): UserModel? {
        return userRepository.getUserByEmailAndPassword(email, password)
    }
}

