package com.iua.app.di.notification

import android.content.Context
import com.iua.app.notification.NotificationHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @Provides
    @Singleton
    fun provideNotificationHandler(
        @ApplicationContext context: Context
    ): NotificationHandler {
        return NotificationHandler(context)
    }
}
