package com.iua.app.di


import com.iua.app.data.repository.EventsRepositoryImpl
import com.iua.app.data.repository.UserRepositoryImpl
import com.iua.app.domain.repository.EventsRepository
import com.iua.app.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindsEventsRepository(
        repositoryImpl: EventsRepositoryImpl
    ): EventsRepository

    @Singleton
    @Binds
    abstract fun bindUserRepository(
        repositoryImpl: UserRepositoryImpl
    ): UserRepository

}