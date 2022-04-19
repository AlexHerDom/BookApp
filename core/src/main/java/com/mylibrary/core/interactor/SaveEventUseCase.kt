package com.mylibrary.core.interactor

import com.mylibrary.core.data.EventRepository
import com.mylibrary.core.domain.EventDescriptionResponse

class SaveEventUseCase (private val eventRepository: EventRepository) {
    suspend operator fun invoke(event: EventDescriptionResponse) = eventRepository.saveEvent(event)
}