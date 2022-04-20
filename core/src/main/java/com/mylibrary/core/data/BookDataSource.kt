package com.mylibrary.core.data

import com.mylibrary.core.domain.Book
import com.mylibrary.core.domain.BooksResponse

interface BookDataSource {
    suspend fun add(book: Book)
    suspend fun getBooks() : Resource<BooksResponse>
    suspend fun remove(book: Book)
}