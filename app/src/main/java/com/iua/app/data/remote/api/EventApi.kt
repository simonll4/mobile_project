package com.iua.app.data.remote.api

import com.iua.app.data.remote.dto.EventDTO
import retrofit2.http.GET

interface EventApi {

    @GET("events")
    suspend fun getEvents(): List<EventDTO>

}