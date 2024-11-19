package com.iua.app.di.data

import android.content.Context
import androidx.room.Room
import com.iua.app.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext appContex: Context): AppDatabase {
        return Room.databaseBuilder(
            appContex,
            AppDatabase::class.java,
            "app-db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideEventsDao(db: AppDatabase) = db.eventDAO()

}