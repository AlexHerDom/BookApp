package com.mylibrary.bookapp.framework.network

import com.mylibrary.core.domain.Author
import com.mylibrary.core.domain.Book
import com.mylibrary.core.domain.Chapter
import com.mylibrary.core.domain.EventResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/api/users")
    suspend fun getEvents(): Response<com.mylibrary.core.domain.EventResponse>

    @GET("/dmitrijt9/book-api-mock/books")
    suspend fun getBooks(): Response<List<com.mylibrary.core.domain.Book>>

    @GET("/dmitrijt9/book-api-mock/authors")
    suspend fun getAuthors(): Response<List<com.mylibrary.core.domain.Author>>

    @GET("/dmitrijt9/book-api-mock/chapters")
    suspend fun getChapters(): Response<List<com.mylibrary.core.domain.Chapter>>
}