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

    @GET("/dmitrijt9/book-api-mock/books")
    suspend fun getBooks(): Response<List<Book>>

    @GET("/dmitrijt9/book-api-mock/authors")
    suspend fun getAuthors(): Response<List<Author>>

    @GET("/dmitrijt9/book-api-mock/chapters")
    suspend fun getChapters(): Response<List<Chapter>>
}