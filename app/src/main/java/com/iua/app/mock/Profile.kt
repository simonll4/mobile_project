package com.iua.app.mock

data class Profile(
    val id: Int,
    val name: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val dni : String,
    val avatar: String
)

data class ProfilePopularList(
    val name: String,
    val description: String,
    val star: String,
    val language: String
)


