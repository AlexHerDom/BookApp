package com.mylibrary.bookapp.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.mylibrary.bookapp.db.tables.EventEntity
import com.mylibrary.bookapp.utils.NetworkHelper
import com.mylibrary.core.data.Resource
import com.mylibrary.core.domain.Event
import com.mylibrary.core.domain.EventDescriptionResponse
import com.mylibrary.core.interactor.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    private val getEventDescriptionUseCase: GetEventDescriptionUseCase,
    private val saveEventUseCase: SaveEventUseCase,
    private val getMyEventsUseCase: GetMyEventsUseCase,
    private val deleteEventUseCase: DeleteEventUseCase
) : ViewModel() {


    fun getEvents() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        emit(getEventsUseCase.invoke())
    }

    fun getEventDescription(eventId: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        emit(getEventDescriptionUseCase.invoke(eventId))
    }

    fun saveEvent(event: EventDescriptionResponse) {
        viewModelScope.launch {
            saveEventUseCase.invoke(event)
        }
    }

    fun getMyEvent() = liveData(Dispatchers.IO) {
        emit(getMyEventsUseCase.invoke())
    }

    fun deleteEventById(eventId: Int) {
        viewModelScope.launch {
            deleteEventUseCase.invoke(eventId)
        }
    }
}
