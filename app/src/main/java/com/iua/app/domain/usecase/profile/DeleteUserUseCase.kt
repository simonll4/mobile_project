package com.iua.app.domain.usecase.profile

import com.iua.app.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(userId: String): Result<Unit> {
        return repository.deleteUser(userId)
    }
}


