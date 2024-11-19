package com.iua.app.di.work

import com.iua.app.domain.repository.EventsRepository
import com.iua.app.notification.NotificationHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkerModule {

    @Provides
    @Singleton
    fun provideCheckFavoritesWorkerFactory(
        eventsRepository: EventsRepository,
        notificationHandler: NotificationHandler
    ): FavoriteEventReminderFactory {
        return FavoriteEventReminderFactory(eventsRepository, notificationHandler)
    }

}