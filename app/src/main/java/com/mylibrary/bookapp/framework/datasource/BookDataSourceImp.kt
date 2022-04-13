package com.mylibrary.bookapp.framework.datasource

import com.mylibrary.bookapp.extensions.convertToDomainBook
import com.mylibrary.bookapp.framework.network.BookService
import com.mylibrary.core.data.BookDataSource
import com.mylibrary.core.data.Resource
import com.mylibrary.core.domain.Book

class BookDataSourceImp(private val api: BookService) : BookDataSource {

    override suspend fun add(book: Book) {

    }

    override suspend fun getBooks(): Resource<List<Book>> {
        return api.getBooks().convertToDomainBook()
    }


    override suspend fun remove(book: Book) {

    }
}