package com.mylibrary.bookapp.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mylibrary.bookapp.utils.NetworkHelper
import com.mylibrary.core.data.Resource
import com.mylibrary.core.interactor.GetEventDescriptionUseCase
import com.mylibrary.core.interactor.GetEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    private val getEventDescriptionUseCase: GetEventDescriptionUseCase,
    private val networkHelper: NetworkHelper
) : ViewModel() {


    fun getEvents() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        emit(getEventsUseCase.invoke())
    }

    fun getEventDescription(eventId: Int) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        emit(getEventDescriptionUseCase.invoke(eventId))
    }
}
