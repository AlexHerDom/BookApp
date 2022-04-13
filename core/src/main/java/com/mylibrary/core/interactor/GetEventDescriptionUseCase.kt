package com.mylibrary.core.interactor

import com.mylibrary.core.data.EventRepository

class GetEventDescriptionUseCase(private val eventRepository: EventRepository) {
    suspend operator fun invoke(eventId: Int) = eventRepository.getEventDescription(eventId)
}