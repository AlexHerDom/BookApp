package com.mylibrary.bookapp.framework.datasource

import com.mylibrary.bookapp.db.AppDao
import com.mylibrary.bookapp.db.tables.EventEntity
import com.mylibrary.bookapp.extensions.convertToDomainEventDB
import com.mylibrary.bookapp.extensions.convertToEventEntity
import com.mylibrary.bookapp.extensions.convertToEvents
import com.mylibrary.core.data.EventDataSource
import com.mylibrary.core.data.Resource
import com.mylibrary.core.domain.*

class DataBaseEventDataSourceImp(private val appDao: AppDao) : EventDataSource {
    override suspend fun getEvents(): Resource<EventResponse> =
        appDao.getMyEvents().convertToDomainEventDB()

    override suspend fun getEventDescription(eventId: Int): Resource<EventDescriptionResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun saveEvent(event: EventDescriptionResponse) = appDao.insertEvent(event.convertToEventEntity())

    override suspend fun getMyEvents(): List<Event> = appDao.getMyEvents().convertToEvents()

    override suspend fun deleteEventById(eventId: Int) = appDao.deleteEventById(eventId)
}