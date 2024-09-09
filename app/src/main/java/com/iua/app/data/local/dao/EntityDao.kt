package com.iua.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iua.app.data.local.entity.EventsEntity

@Dao
interface EntityDao {

    @Query("SELECT * FROM events")
    suspend fun getEntity(): MutableList<EventsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(eventsEntity: EventsEntity): Long
}