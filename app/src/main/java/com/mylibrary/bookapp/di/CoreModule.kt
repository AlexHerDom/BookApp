package com.mylibrary.bookapp.di

import com.mylibrary.bookapp.framework.datasource.MyFakeLibraryBookDataSource
import com.mylibrary.bookapp.framework.datasource.MyLibraryBookDataSource
import com.mylibrary.bookapp.framework.network.ApiInterface
import com.mylibrary.bookapp.framework.network.BookService
import com.mylibrary.bookapp.framework.network.RetrofitClient
import com.mylibrary.core.data.BookDataSource
import com.mylibrary.core.data.BoookRepository
import com.mylibrary.core.interactor.GetBooksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @MyBookDataSource
    @Singleton
    @Provides
    fun provideBookDataSource(apiService: BookService): BookDataSource {
        return MyLibraryBookDataSource(apiService)
    }

    @FakeBookDataSource
    @Singleton
    @Provides
    fun provideFakeBookDataSource(): BookDataSource {
        return MyFakeLibraryBookDataSource()
    }

    @Singleton
    @Provides
    fun provideBoookRepository(@MyBookDataSource bookDataSource: BookDataSource,
                               @FakeBookDataSource fakeBookDataSource: BookDataSource
                               ): BoookRepository = BoookRepository(bookDataSource, fakeBookDataSource)

    @Singleton
    @Provides
    fun provideBookUseCase(bookRepository: BoookRepository): GetBooksUseCase = GetBooksUseCase(bookRepository)

}