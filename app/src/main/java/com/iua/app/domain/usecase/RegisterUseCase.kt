package com.iua.app.domain.usecase

import com.iua.app.domain.model.Resource
import com.iua.app.domain.repository.UserRepository
import com.iua.app.domain.model.UserModel
import com.iua.app.domain.model.toUserDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(firstName: String, lastName: String, email: String, password: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())

            // Convertir UserModel a UserDTO
            val userModel = UserModel(0, firstName, lastName, email, password)
            val userDTO = userModel.toUserDTO()

            val response = userRepository.registerUser(userDTO)
            if (response) {
                emit(Resource.Success(true))
            } else {
                emit(Resource.Error("Registration failed"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An error occurred"))
        }
    }
}
