package com.mylibrary.core.interactor

import com.mylibrary.core.data.BoookRepository
import com.mylibrary.core.domain.Book

class RemoveBookUseCase(private val bookRepository: BoookRepository) {
    suspend operator fun invoke(book: Book) = bookRepository.remove(book)
}