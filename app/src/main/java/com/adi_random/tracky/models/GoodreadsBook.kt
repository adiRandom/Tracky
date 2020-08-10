package com.adi_random.tracky.models

import android.os.Parcelable
import androidx.databinding.ObservableBoolean
import androidx.recyclerview.widget.DiffUtil
import androidx.room.*
import com.adi_random.tracky.ui.main.readingList.ReadingListType
import kotlinx.android.parcel.Parcelize
import java.util.*

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
    var owner: ReadingListType? = ReadingListType.UNSET,
    var addedAt: Date?
) : Parcelable {
    constructor(
        id: Int,
        original_publication_year: Int,
        original_publication_month: Int,
        original_publication_day: Int,
        average_rating: Float,
        best_book: BestBook,
        owner: ReadingListType? = ReadingListType.UNSET, canBeAddedToReadingList: Boolean,
        addedAt: Date?
    ) : this(
        id,
        original_publication_year,
        original_publication_month,
        original_publication_day,
        average_rating,
        best_book,
        owner,
        addedAt
    ) {

        this.canBeAddedToReadingList.set(canBeAddedToReadingList)
    }

    @Ignore
//    TODO: Check the warning
    var canBeAddedToReadingList: ObservableBoolean = ObservableBoolean(true)

}


@Parcelize
data class Author(@ColumnInfo(name = "author_id") val id: Int, val name: String) : Parcelable


object GoodreadsBookComparator : DiffUtil.ItemCallback<GoodreadsBook>() {
    override fun areItemsTheSame(oldItem: GoodreadsBook, newItem: GoodreadsBook): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GoodreadsBook, newItem: GoodreadsBook): Boolean {
        return oldItem.id == newItem.id
    }

}

class GoodreadsBookTypeConverter {
    @TypeConverter
    fun fromReadingListTypeToInt(it: ReadingListType) = it.value

    @TypeConverter
    fun fromIntToReadingListType(it: Int) = ReadingListType.getType(it)

    @TypeConverter
    fun fromDateToLong(it: Date) = it.time

    @TypeConverter
    fun fromLongToDate(it: Long) = Date(it)
}