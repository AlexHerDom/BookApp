package com.mylibrary.core.data

import com.mylibrary.core.domain.Event
import com.mylibrary.core.domain.EventDescriptionResponse
import com.mylibrary.core.domain.EventResponse

interface EventDataSource {
    suspend fun getEvents(): Resource<EventResponse>
    suspend fun getEventDescription(eventId: Int): Resource<EventDescriptionResponse>
    suspend fun saveEvent(event: EventDescriptionResponse)
    suspend fun getMyEvents(): List<Event>
    suspend fun deleteEventById(eventId: Int)
}