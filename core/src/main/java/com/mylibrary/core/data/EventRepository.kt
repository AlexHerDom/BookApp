package com.mylibrary.core.data

import com.mylibrary.core.domain.Event
import com.mylibrary.core.domain.EventDescriptionResponse
import com.mylibrary.core.domain.EventResponse

class EventRepository(
    private val dataSource: EventDataSource,
    private val dataBaseDataSource: EventDataSource
) {
    suspend fun getEvents(): Resource<EventResponse> = dataSource.getEvents()

    suspend fun getEventDescription(eventId: Int): Resource<EventDescriptionResponse> =
        dataSource.getEventDescription(eventId)

    suspend fun saveEvent(event: EventDescriptionResponse) = dataBaseDataSource.saveEvent(event)

    suspend fun getMyEvents(): List<Event> = dataBaseDataSource.getMyEvents()

    suspend fun deleteEventById(eventId: Int) = dataBaseDataSource.deleteEventById(eventId)

}