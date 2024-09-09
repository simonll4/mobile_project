package com.iua.app.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.iua.app.data.local.dao.EntityDao
import com.iua.app.data.local.entity.EventsEntity

@Database(entities = [EventsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun entityDao(): EntityDao
}