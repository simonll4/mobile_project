package com.iua.app.data.remote.dto

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class EventDTO(
    val id: Long,
    val title: String,
    val subtitle: String,
    val description: String,
    val image: String,
    val date: String,
    val location: String,
) {

    fun getDateAsDate(): Date {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        return format.parse(date) ?: Date()
    }
}