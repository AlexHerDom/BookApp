package com.mylibrary.core.domain

data class EventResponse(
    val data: List<Event> = listOf(),
    val page: Int = 0,
    val per_page: Int = 0,
    val support: Support? = null,
    val total: Int = 0,
    val total_pages: Int = 0
)