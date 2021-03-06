package com.mylibrary.core.data

import com.mylibrary.core.domain.Book
import com.mylibrary.core.domain.BooksResponse

class BookRepository(
    private val dataSource: BookDataSource,
    private val fakeDataSource: BookDataSource
) {
    suspend fun add(book: Book) {
        dataSource.add(book)
        fakeDataSource.add(book)
    }

    suspend fun getBooks(): Resource<BooksResponse> = dataSource.getBooks()

    suspend fun remove(book: Book) = dataSource.remove(book)


}