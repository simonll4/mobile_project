package com.iua.app.data.remote.api

import com.iua.app.data.remote.dto.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {


    @GET("/users")
    suspend fun getUserByEmailAndPassword(
        @Query("email") email: String,
        @Query("password") password: String
    ): List<UserDTO>

    @POST("/users")
    suspend fun registerUser(
        @Body user: UserDTO
    ): Response<Unit>

}
