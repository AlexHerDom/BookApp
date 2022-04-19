package com.mylibrary.bookapp.di

import android.app.Application
import android.content.Context
import com.mylibrary.bookapp.db.AppDao
import com.mylibrary.bookapp.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Singleton
    @Provides
    fun provideAppDB(context: Application): AppDatabase = AppDatabase.getAppDB(context)

    @Singleton
    @Provides
    fun provideDao(appDatabase: AppDatabase): AppDao = appDatabase.getDAO()
}