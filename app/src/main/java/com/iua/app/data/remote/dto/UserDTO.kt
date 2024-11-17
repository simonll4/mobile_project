package com.iua.app.data.remote.dto

data class UserDTO(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)
