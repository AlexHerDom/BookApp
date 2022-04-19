package com.mylibrary.core.interactor

import com.mylibrary.core.data.EventRepository
import com.mylibrary.core.domain.Event
import com.mylibrary.core.domain.EventDescriptionResponse

class DeleteEventUseCase(private val eventRepository: EventRepository) {
    suspend operator fun invoke(eventId: Int) = eventRepository.deleteEventById(eventId)
}