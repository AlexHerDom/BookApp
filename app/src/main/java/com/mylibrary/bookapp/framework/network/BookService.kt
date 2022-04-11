package com.mylibrary.bookapp.framework.network

import javax.inject.Inject

class BookService @Inject constructor(private val api: ApiInterface) {
    suspend fun getBooks() = api.getBooks()
}


