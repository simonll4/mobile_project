package com.iua.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iua.app.data.local.dao.EventDAO
import com.iua.app.data.local.entity.EventEntity

@Database(entities = [EventEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDAO(): EventDAO
}