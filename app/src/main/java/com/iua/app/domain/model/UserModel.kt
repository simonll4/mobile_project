package com.iua.app.domain.model

import com.iua.app.data.remote.dto.UserDTO

data class UserModel(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)

// DTO -> Domain Model
fun UserDTO.toUserModel(): UserModel {
    return UserModel(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        password = this.password
    )
}

// Domain Model -> DTO
fun UserModel.toUserDTO(): UserDTO {
    return UserDTO(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        password = this.password
    )
}
