package com.mylibrary.bookapp.di

import com.mylibrary.bookapp.framework.datasource.BookDataSourceImp
import com.mylibrary.bookapp.framework.datasource.EventDataSourceImp

import com.mylibrary.bookapp.framework.datasource.FakeBookDataSourceImp
import com.mylibrary.bookapp.framework.datasource.FakeEventDataSource

import com.mylibrary.bookapp.framework.network.BookService
import com.mylibrary.bookapp.framework.network.EventService
import com.mylibrary.core.data.BookDataSource

import com.mylibrary.core.data.BookRepository
import com.mylibrary.core.data.EventDataSource
import com.mylibrary.core.data.EventRepository

import com.mylibrary.core.interactor.GetBooksUseCase
import com.mylibrary.core.interactor.GetEventDescriptionUseCase
import com.mylibrary.core.interactor.GetEventsUseCase

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

    @FakeEventDataSourceQualifier
    @Singleton
    @Provides
    fun provideFakeEventDataSource(): EventDataSource = FakeEventDataSource()

    @Singleton
    @Provides
    fun provideEventRepository(
        @EventDataSourceQualifier eventDataSource: EventDataSource,
        @FakeEventDataSourceQualifier fakeEventDataSource: EventDataSource
    ) = EventRepository(eventDataSource, fakeEventDataSource)

    @Singleton
    @Provides
    fun provideEventUseCase(eventRepository: EventRepository): GetEventsUseCase =
        GetEventsUseCase(eventRepository)

    @Singleton
    @Provides
    fun provideGetEventDescriptionUseCase(eventRepository: EventRepository): GetEventDescriptionUseCase =
        GetEventDescriptionUseCase(eventRepository)
}