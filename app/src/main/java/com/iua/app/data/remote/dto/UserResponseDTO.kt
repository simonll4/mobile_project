package com.iua.app.data.remote.dto

data class UserResponseDTO(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)