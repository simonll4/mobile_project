//// File: app/src/main/java/com/iua/app/di/WorkerModule.kt
//package com.iua.app.di
//
//import androidx.work.ListenableWorker
//import com.iua.app.ui.work.CheckFavoritesWorker
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Provider
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object WorkerModule {
//
//    @Provides
//    @Singleton
//    fun provideWorkerFactory(
//        checkFavoritesWorkerFactory: Provider<CheckFavoritesWorker.Factory>
//    ): Map<Class<out ListenableWorker>, Provider<CheckFavoritesWorker.Factory>> {
//        return mapOf(
//            CheckFavoritesWorker::class.java to checkFavoritesWorkerFactory
//        )
//    }
//}