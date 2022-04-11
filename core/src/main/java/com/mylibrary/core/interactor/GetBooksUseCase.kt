package com.mylibrary.core.interactor

import com.mylibrary.core.data.BoookRepository

class GetBooksUseCase(private val bookRepository: BoookRepository) {
    suspend operator fun invoke() = bookRepository.getBooks()
}