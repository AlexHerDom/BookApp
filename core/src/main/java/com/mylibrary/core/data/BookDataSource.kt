package com.mylibrary.core.data

import com.mylibrary.core.domain.Book

interface BookDataSource {
    suspend fun add(book: Book)
    suspend fun getBooks() : Resource<List<Book>>
    suspend fun remove(book: Book)
}