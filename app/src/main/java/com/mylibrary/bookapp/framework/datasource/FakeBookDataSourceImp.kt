package com.mylibrary.bookapp.framework.datasource

import com.mylibrary.core.data.BookDataSource
import com.mylibrary.core.data.Resource
import com.mylibrary.core.domain.Book

class FakeBookDataSourceImp() : BookDataSource {

    override suspend fun add(book: Book) {

    }

    override suspend fun getBooks(): Resource<List<Book>> {
        return Resource.success(listOf(Book(1,"cover", 2, "isbn", 4,"release", "title")))
    }

    override suspend fun remove(book: Book) {

    }
}