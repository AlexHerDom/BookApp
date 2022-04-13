package com.mylibrary.core.data

import com.mylibrary.core.domain.EventDescriptionResponse
import com.mylibrary.core.domain.EventResponse

class EventRepository(
    private val dataSource: EventDataSource,
    private val fakeDataSource: EventDataSource
) {
    suspend fun getEvents(): Resource<EventResponse> = dataSource.getEvents()

    suspend fun getEventDescription(eventId: Int):Resource<EventDescriptionResponse> = dataSource.getEventDescription(eventId)
}