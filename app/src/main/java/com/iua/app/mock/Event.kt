package com.iua.app.mock

data class Event(
    val id: String,
    val title: String,
    val subtitle: String? = null,
    val description: String,
    val image: String
)