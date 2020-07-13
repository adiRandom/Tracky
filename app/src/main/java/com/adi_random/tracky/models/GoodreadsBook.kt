package com.adi_random.tracky.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BestBook(
    val id: Int,
    val title: String,
    val author: Author,
    val image_url: String,
    val small_image_url: String
) : Parcelable

@Parcelize
data class GoodreadsBook(
    val id: Int,
    val original_publication_year: Int,
    val original_publication_month: Int,
    val original_publication_day: Int,
    val average_rating: Float,
    val best_book: BestBook
) : Parcelable


@Parcelize
data class Author(val id: Int, val name: String) : Parcelable