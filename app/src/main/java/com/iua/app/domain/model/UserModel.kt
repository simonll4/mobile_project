package com.iua.app.domain.model


import com.iua.app.data.remote.dto.UserRequestDTO
import com.iua.app.data.remote.dto.UserResponseDTO

data class UserModel(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)

// Responde DTO -> Domain Model
fun UserResponseDTO.toUserModel(): UserModel {
    return UserModel(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        password = this.password
    )
}

// Domain Model -> Request DTO
fun UserModel.toUserRequestDTO(): UserRequestDTO {
    return UserRequestDTO(
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        password = this.password
    )
}