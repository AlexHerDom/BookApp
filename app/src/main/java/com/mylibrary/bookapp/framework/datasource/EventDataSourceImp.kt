package com.mylibrary.bookapp.framework.datasource

import com.mylibrary.bookapp.extensions.convertToDomainEvent
import com.mylibrary.bookapp.extensions.convertToDomainEventDescription
import com.mylibrary.bookapp.framework.network.EventService
import com.mylibrary.core.data.EventDataSource
import com.mylibrary.core.data.Resource
import com.mylibrary.core.domain.EventDescriptionResponse
import com.mylibrary.core.domain.EventResponse

class EventDataSourceImp(private val api: EventService) : EventDataSource {
    override suspend fun getEvents(): Resource<EventResponse> =
        api.getEvents().convertToDomainEvent()

    override suspend fun getEventDescription(eventId: Int): Resource<EventDescriptionResponse> =
        api.getEventDescription(eventId).convertToDomainEventDescription()
}