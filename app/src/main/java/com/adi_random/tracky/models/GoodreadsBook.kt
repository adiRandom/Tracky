package com.adi_random.tracky.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adi_random.tracky.ui.main.readingList.ReadingListType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BestBook(
    @ColumnInfo(name = "best_book_id")
    val id: Int,
    val title: String,
    @Embedded
    val author: Author,
    val image_url: String,
    val small_image_url: String
) : Parcelable

@Parcelize
@Entity
data class GoodreadsBook(
    @PrimaryKey val id: Int,
    val original_publication_year: Int,
    val original_publication_month: Int,
    val original_publication_day: Int,
    val average_rating: Float,
    @Embedded
    val best_book: BestBook,
    var owner: ReadingListType? = ReadingListType.UNSET
) : Parcelable


@Parcelize
data class Author(@ColumnInfo(name = "author_id") val id: Int, val name: String) : Parcelable