package com.mylibrary.bookapp.framework.network

import com.mylibrary.core.domain.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/api/users")
    suspend fun getEvents(): Response<EventResponse>

    @GET("/api/users/{eventId}")
    suspend fun getEventDescription(@Path("eventId") eventId: Int): Response<EventDescriptionResponse>

    @GET("/api/unknown")
    suspend fun getBooks(): Response<BooksResponse>
}