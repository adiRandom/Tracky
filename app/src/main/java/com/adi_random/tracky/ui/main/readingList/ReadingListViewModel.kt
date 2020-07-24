package com.adi_random.tracky.ui.main.readingList

import android.app.Application
import android.os.Parcelable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.TypeConverter
import com.adi_random.tracky.database.Database
import com.adi_random.tracky.models.GoodreadsBook
import kotlinx.android.parcel.Parcelize

class ReadingListViewModel(app: Application, private val type: ReadingListType) :
    AndroidViewModel(app) {

    val readingList: LiveData<List<GoodreadsBook>> by lazy {
        Database.getInstance(app.applicationContext).goodreadsBookDao().getReadingList(type)
    }

}

@Parcelize
enum class ReadingListType(val value: Int) : Parcelable {
    TO_BE_READ(0), READING(1), DONE(2), UNSET(3);

    companion object {
        fun getType(value: Int) = ReadingListType.values().first { it.value == value }
    }
}

class ReadingListTypeConverter {
    @TypeConverter
    fun fromReadingListTypeToInt(it: ReadingListType) = it.value

    @TypeConverter
    fun fromIntToReadingListType(it: Int) = ReadingListType.getType(it)
}