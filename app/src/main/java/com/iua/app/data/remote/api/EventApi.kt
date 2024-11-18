package com.iua.app.data.remote.api

import com.iua.app.data.remote.dto.EventDTO
import retrofit2.http.GET

interface EventApi {

    // trae los eventos del servidor mockeado
    @GET("events")
    suspend fun getEvents(): List<EventDTO>

}