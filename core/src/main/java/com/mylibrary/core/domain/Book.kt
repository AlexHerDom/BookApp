package com.mylibrary.core.domain

data class Book(
    val author_id: Int,
    val cover_image: String,
    val id: Int,
    val isbn: String,
    val pages: Int,
    val releaseDate: String,
    val title: String
)