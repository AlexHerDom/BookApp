package com.mylibrary.core.interactor

import com.mylibrary.core.data.EventRepository

class GetMyEventsUseCase(private val eventRepository: EventRepository) {
    suspend operator fun invoke() = eventRepository.getMyEvents()
}