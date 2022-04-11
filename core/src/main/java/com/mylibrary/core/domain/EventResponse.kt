package com.mylibrary.core.domain

data class EventResponse(
    val data: List<Event>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)