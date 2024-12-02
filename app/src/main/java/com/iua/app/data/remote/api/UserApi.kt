package com.iua.app.data.remote.api

import com.iua.app.data.remote.dto.UserRequestDTO
import com.iua.app.data.remote.dto.UserResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    // simula login
    @GET("/users")
    suspend fun getUserByEmailAndPassword(
        @Query("email") email: String, @Query("password") password: String
    ): List<UserResponseDTO>

    // simula registro
//    @POST("/users")
//    suspend fun registerUser(
//        @Body user: UserRequestDTO
//    ): Response<Unit>
    // Simula registro
    @POST("/users")
    suspend fun registerUser(
        @Body user: UserRequestDTO
    ): Response<UserResponseDTO>


    // actualiza un campo de un usuario
    @PATCH("/users/{id}")
    suspend fun updateUserField(
        @Path("id") userId: String, @Body updateRequest: Map<String, String>
    ): Response<UserResponseDTO>

    // simula borrado de cuenta
    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") userId: String): Response<Unit>

}
