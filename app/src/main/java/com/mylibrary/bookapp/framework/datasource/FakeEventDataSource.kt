package com.mylibrary.bookapp.framework.datasource

import com.mylibrary.core.data.EventDataSource
import com.mylibrary.core.data.Resource
import com.mylibrary.core.domain.*

class FakeEventDataSource() : EventDataSource {
    override suspend fun getEvents(): Resource<EventResponse> {
        return Resource.success(
            EventResponse(
                listOf(Event("", "", "", 0, "")),
                0,
                0,
                Support("", ""),
                0,
                0
            )
        )
    }

    override suspend fun getEventDescription(eventId: Int): Resource<EventDescriptionResponse> {
        TODO("Not yet implemented")
    }
}