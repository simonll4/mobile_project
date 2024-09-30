package com.iua.app.mock

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val subtitle: String? = null,
    val image: String
)