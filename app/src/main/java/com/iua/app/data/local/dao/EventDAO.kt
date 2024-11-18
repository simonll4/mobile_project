package com.iua.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iua.app.data.local.entity.EventEntity

@Dao
interface EventDAO {

    // obtiene un evento por id
    @Query("SELECT * FROM events WHERE id = :id LIMIT 1")
    suspend fun getEventById(id: Long): EventEntity?

    // obtiene todos los eventos
    @Query("SELECT * FROM events")
    suspend fun getEvents(): MutableList<EventEntity>

    // obtiene los eventos favoritos
    @Query("SELECT * FROM events WHERE is_favorite = 1")
    suspend fun getFavoriteEvents(): List<EventEntity>

    // inserta eventos del server en la base de datos local
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(events: List<EventEntity>)

    // cambia el estado de favorito de un evento
    @Query("UPDATE events SET is_favorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean): Int

}

