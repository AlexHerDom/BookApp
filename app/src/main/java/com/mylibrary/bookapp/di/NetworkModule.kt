package com.mylibrary.bookapp.di

import com.mylibrary.bookapp.framework.network.RetrofitClient
import com.mylibrary.bookapp.framework.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = RetrofitClient.getInstance()

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)

}