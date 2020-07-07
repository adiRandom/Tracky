package com.adi_random.tracky.models

import android.graphics.Bitmap

data class BestBook(
    val id: Int,
    val title: String,
    val author: Author,
    val image_url: String,
    val small_image_url: String,
    var imageBitmap: Bitmap?
)


data class GoodreadsBook(
    val id: Int,
    val original_publication_year: Int,
    val original_publication_month: Int,
    val original_publication_day: Int,
    val average_rating: Float,
    val best_book: BestBook
)

data class Author(val id: Int, val name: String)