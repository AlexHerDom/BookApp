package com.mylibrary.core.interactor

import com.mylibrary.core.data.BookRepository
import com.mylibrary.core.domain.Book

class RemoveBookUseCase(private val bookRepository: BookRepository) {
    suspend operator fun invoke(book: Book) = bookRepository.remove(book)
}