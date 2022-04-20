package com.mylibrary.bookapp.extensions

import com.mylibrary.bookapp.db.tables.EventEntity
import com.mylibrary.core.data.Resource
import com.mylibrary.core.domain.*
import retrofit2.Response

fun Response<BooksResponse>.convertToDomainBook(): Resource<BooksResponse> {
    return if (this.isSuccessful)
        Resource.success(this.body())
    else Resource.error(msg = "Ocurrio un error", null)
}


fun Response<EventResponse>.convertToDomainEvent(): Resource<EventResponse> {
    return if (this.isSuccessful)
        Resource.success(this.body())
    else Resource.error(msg = "Ocurrio un error", null)
}

fun Response<EventDescriptionResponse>.convertToDomainEventDescription(): Resource<EventDescriptionResponse> {
    return if (this.isSuccessful)
        Resource.success(this.body())
    else Resource.error(msg = "Ocurrio un error", null)
}

fun List<EventEntity>.convertToDomainEventDB(): Resource<EventResponse> {

    val list = ArrayList<Event>()

    this.forEach {
        list.add(
            Event(
                avatar = it.avatar,
                email = it.email,
                first_name = it.first_name,
                id = it.id,
                last_name = it.last_name
            )
        )
    }

    return Resource.success(
        EventResponse(
            list
        )
    )
}

fun EventDescriptionResponse.convertToEventEntity() = EventEntity(
    this.data.id,
    this.data.avatar,
    this.data.email,
    this.data.first_name,
    this.data.last_name
)

fun List<EventEntity>.convertToEvents(): List<Event> {
    val events = ArrayList<Event>()
    this.forEach {
        events.add(
            Event(
                id = it.id,
                avatar = it.avatar,
                email = it.email,
                first_name = it.first_name,
                last_name = it.last_name
            )
        )
    }
    return events
}