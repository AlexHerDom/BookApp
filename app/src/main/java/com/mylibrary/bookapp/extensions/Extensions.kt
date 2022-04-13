package com.mylibrary.bookapp.extensions

import com.mylibrary.core.data.Resource
import com.mylibrary.core.domain.Book
import com.mylibrary.core.domain.EventDescriptionResponse
import com.mylibrary.core.domain.EventResponse
import retrofit2.Response

fun Response<List<Book>>.convertToDomainBook(): Resource<List<Book>> {
    return if(this.isSuccessful)
        Resource.success(this.body().orEmpty())
    else Resource.error(msg =  "Ocurrio un error", null)
}

fun Response<EventResponse>.convertToDomainEvent(): Resource<EventResponse> {
    return if(this.isSuccessful)
        Resource.success(this.body())
    else Resource.error(msg =  "Ocurrio un error", null)
}

fun Response<EventDescriptionResponse>.convertToDomainEventDescription(): Resource<EventDescriptionResponse> {
    return if(this.isSuccessful)
        Resource.success(this.body())
    else Resource.error(msg =  "Ocurrio un error", null)
}