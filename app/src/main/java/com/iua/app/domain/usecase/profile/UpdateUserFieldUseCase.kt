package com.iua.app.domain.usecase.profile

import com.iua.app.domain.model.UserModel
import com.iua.app.domain.repository.UserRepository
import javax.inject.Inject


class UpdateUserFieldUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(userId: String, field: String, value: String): UserModel? {
        return userRepository.updateUserField(userId, field, value)
    }
}


//class UpdateUserFieldUseCase @Inject constructor(
//    private val repository: UserRepository
//) {
//    operator fun invoke(userId: String, field: String, value: String): Flow<Resource<UserModel?>> = flow {
//        try {
//            emit(Resource.Loading())
//            val updatedUser = repository.updateUserField(userId, field, value)
//            if (updatedUser != null) {
//                emit(Resource.Success(updatedUser))
//            } else {
//                emit(Resource.Error("Error updating field"))
//            }
//        } catch (e: Exception) {
//            emit(Resource.Error(e.message ?: "An unknown error occurred"))
//        }
//    }
//}


//class UpdateUserFieldUseCase @Inject constructor(
//    private val repository: UserRepository
//) {
//    operator fun invoke(userId: String, field: String, value: String): Flow<Resource<Boolean>> =
//        flow {
//            try {
//                emit(Resource.Loading())
//                val result = repository.updateUserField(userId, field, value)
//                if (result) {
//                    emit(Resource.Success(result))
//                } else {
//                    emit(Resource.Error("Error updating field"))
//                }
//            } catch (e: Exception) {
//                emit(Resource.Error(e.message ?: "An unknown error occurred"))
//            }
//        }
//}
