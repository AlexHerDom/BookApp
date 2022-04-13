package com.mylibrary.core.interactor

import com.mylibrary.core.data.BookRepository

class GetBooksUseCase(private val bookRepository: BookRepository) {
    suspend operator fun invoke() = bookRepository.getBooks()
}