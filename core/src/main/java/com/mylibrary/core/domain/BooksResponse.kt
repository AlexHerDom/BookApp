package com.mylibrary.core.domain

data class BooksResponse(
    val `data`: List<DataX> = listOf(),
    val page: Int = 0,
    val per_page: Int = 0,
    val support: SupportXX = SupportXX("", ""),
    val total: Int = 0,
    val total_pages: Int = 0
)