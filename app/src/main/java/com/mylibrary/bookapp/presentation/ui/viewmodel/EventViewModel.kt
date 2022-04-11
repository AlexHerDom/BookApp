package com.mylibrary.bookapp.presentation.ui.viewmodel

/*import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mylibrary.core.domain.EventResponse
import com.mylibrary.bookapp.domain.GetEventsUseCase
import com.mylibrary.bookapp.utils.NetworkHelper
import com.mylibrary.core.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject*/

/*
@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventsUseCase: GetEventsUseCase,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    private val events = MutableLiveData<Resource<com.mylibrary.core.domain.EventResponse>>()
    val eventLiveData: LiveData<Resource<com.mylibrary.core.domain.EventResponse>> = events

    fun getEvents() = viewModelScope.launch(Dispatchers.IO) {
        events.postValue(Resource.loading(null))

        if (networkHelper.isNetworkConnected()) {
            eventsUseCase().let {
                if (it.isSuccessful)
                    events.postValue(Resource.success(it.body()))
                else
                    events.postValue(Resource.error(it.errorBody().toString(), null))
            }
        } else events.postValue(Resource.error("No internet connection", null))
    }
}*/
