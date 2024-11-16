package com.iua.app.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.iua.app.data.local.dao.EventDAO
import com.iua.app.data.local.entity.EventEntity

@Database(entities = [EventEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun entityDao(): EventDAO

}