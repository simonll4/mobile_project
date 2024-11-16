package com.iua.app.data.local.dao

import android.provider.CalendarContract
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.iua.app.data.local.entity.EventEntity

@Dao
interface EventDAO {

    @Query("SELECT * FROM events")
    suspend fun getEntity(): MutableList<EventEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(eventEntity: EventEntity): Long

    @Query("SELECT * FROM events WHERE is_favorite = 1")
    suspend fun getFavoriteEvents(): List<EventEntity>

    //@Update
    //suspend fun updateEvent(eventEntity: EventEntity): Int

    @Query("UPDATE events SET is_favorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean): Int

}

