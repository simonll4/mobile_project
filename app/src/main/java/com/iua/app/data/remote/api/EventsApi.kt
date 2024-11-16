package com.iua.app.data.remote.api

import com.iua.app.data.remote.dto.EventDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EventsApi {

    @GET("events")
    suspend fun getEvents(): List<EventDTO>

    @POST("events")
    suspend fun saveEvent(@Body event: EventDTO): Boolean

}