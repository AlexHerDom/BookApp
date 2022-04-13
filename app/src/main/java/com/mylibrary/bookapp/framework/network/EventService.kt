package com.mylibrary.bookapp.framework.network

import javax.inject.Inject

class EventService @Inject constructor(private val api: ApiInterface) {
    suspend fun getEvents()  = api.getEvents()
    suspend fun getEventDescription(eventId: Int) = api.getEventDescription(eventId)
}