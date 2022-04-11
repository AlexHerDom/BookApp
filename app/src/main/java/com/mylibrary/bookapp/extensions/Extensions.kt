package com.mylibrary.bookapp.extensions

import com.mylibrary.core.data.Resource
import com.mylibrary.core.domain.Book
import retrofit2.Response

fun Response<List<Book>>.convertToDomainBook(): Resource<List<Book>> {
    return if(this.isSuccessful)
        Resource.success(this.body().orEmpty())
    else Resource.error(msg =  "Ocurrio un error", null)
}