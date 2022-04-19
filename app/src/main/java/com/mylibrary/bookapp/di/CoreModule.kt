package com.mylibrary.bookapp.di

import com.mylibrary.bookapp.db.AppDao
import com.mylibrary.bookapp.framework.datasource.BookDataSourceImp
import com.mylibrary.bookapp.framework.datasource.EventDataSourceImp

import com.mylibrary.bookapp.framework.datasource.FakeBookDataSourceImp
import com.mylibrary.bookapp.framework.datasource.DataBaseEventDataSourceImp

import com.mylibrary.bookapp.framework.network.BookService
import com.mylibrary.bookapp.framework.network.EventService
import com.mylibrary.core.data.BookDataSource

import com.mylibrary.core.data.BookRepository
import com.mylibrary.core.data.EventDataSource
import com.mylibrary.core.data.EventRepository
import com.mylibrary.core.interactor.*

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @BookDataSourceQualifier
    @Singleton
    @Provides
    fun provideBookDataSource(apiService: BookService): BookDataSource =
        BookDataSourceImp(apiService)

    @FakeBookDataSourceQualifier
    @Singleton
    @Provides
    fun provideFakeBookDataSource(): BookDataSource = FakeBookDataSourceImp()

    @Singleton
    @Provides
    fun provideBookRepository(
        @BookDataSourceQualifier bookDataSourceImp: BookDataSource,
        @FakeBookDataSourceQualifier fakeBookDataSourceImp: BookDataSource
    ) = BookRepository(bookDataSourceImp, fakeBookDataSourceImp)

    @Singleton
    @Provides
    fun provideBookUseCase(bookRepository: BookRepository): GetBooksUseCase =
        GetBooksUseCase(bookRepository)

    @EventDataSourceQualifier
    @Singleton
    @Provides
    fun provideEventDataSource(apiService: EventService): EventDataSource =
        EventDataSourceImp(apiService)

    @DBDataSourceQualifier
    @Singleton
    @Provides
    fun provideDataBaseEventDataSource(appDao: AppDao): EventDataSource =
        DataBaseEventDataSourceImp(appDao)

    @Singleton
    @Provides
    fun provideEventRepository(
        @EventDataSourceQualifier eventDataSource: EventDataSource,
        @DBDataSourceQualifier dbEventDataSource: EventDataSource
    ) = EventRepository(eventDataSource, dbEventDataSource)

    @Singleton
    @Provides
    fun provideEventUseCase(eventRepository: EventRepository): GetEventsUseCase =
        GetEventsUseCase(eventRepository)

    @Singleton
    @Provides
    fun provideGetEventDescriptionUseCase(eventRepository: EventRepository): GetEventDescriptionUseCase =
        GetEventDescriptionUseCase(eventRepository)

    @Singleton
    @Provides
    fun provideSaveEventUseCase(eventRepository: EventRepository): SaveEventUseCase =
        SaveEventUseCase(eventRepository)

    @Singleton
    @Provides
    fun provideGetMyEventsEventUseCase(eventRepository: EventRepository): GetMyEventsUseCase =
        GetMyEventsUseCase(eventRepository)

    @Singleton
    @Provides
    fun provideDeleteEventUseCase(eventRepository: EventRepository): DeleteEventUseCase =
        DeleteEventUseCase(eventRepository)
}