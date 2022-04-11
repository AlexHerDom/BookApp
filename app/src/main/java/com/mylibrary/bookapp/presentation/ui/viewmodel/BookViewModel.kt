package com.mylibrary.bookapp.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mylibrary.bookapp.utils.NetworkHelper
import com.mylibrary.core.data.Resource
import com.mylibrary.core.interactor.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    fun getBooks() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        emit(getBooksUseCase.invoke())

       /* if (networkHelper.isNetworkConnected()) {
            booksUseCase().let {
                if (it.isSuccessful)
                    books.postValue(Resource.success(it.body()))
                else
                    books.postValue(Resource.error(it.errorBody().toString(), null))
            }
        } else books.postValue(Resource.error("No internet connection", null))*/
    }
}