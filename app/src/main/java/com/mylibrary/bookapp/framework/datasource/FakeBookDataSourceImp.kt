package com.mylibrary.bookapp.framework.datasource

import com.mylibrary.core.data.BookDataSource
import com.mylibrary.core.data.Resource
import com.mylibrary.core.domain.Book
import com.mylibrary.core.domain.BooksResponse

class FakeBookDataSourceImp() : BookDataSource {

    override suspend fun add(book: Book) {

    }

    override suspend fun getBooks(): Resource<BooksResponse> {
        return Resource.success(BooksResponse())
    }

    override suspend fun remove(book: Book) {

    }
}