package com.adi_random.tracky.ui.main.readingList

import android.app.Application
import android.os.Parcelable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.TypeConverter
import com.adi_random.tracky.database.Database
import com.adi_random.tracky.models.GoodreadsBook
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReadingListViewModel(private val app: Application, private val type: ReadingListType) :
    AndroidViewModel(app) {

    val readingList: LiveData<List<GoodreadsBook>> by lazy {
        Database.getInstance(app.applicationContext).goodreadsBookDao()
            .getReadingListAsLiveData(type)
    }

//    fun removeitem(pos: Int) {
//        val filteredList = readingList.value?.filterIndexed() { _pos, _ -> _pos != pos }
//        readingList.postValue(filteredList)
//    }


    //    Move item to the next list
    fun moveToTheNextList(pos: Int) {

        val item = readingList.value?.get(pos)

        //Update the item in memory
        if (item?.owner != null) {
            val newOwner = ReadingListType.getType(item.owner!!.value + 1)
            item.owner = newOwner


            //Update the item in the database


            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    val db = Database.getInstance(app.applicationContext)
                    db.goodreadsBookDao().updateBook(item)
                }
            }
        }
    }

}

@Parcelize
enum class ReadingListType(val value: Int) : Parcelable {
    TO_BE_READ(0), READING(1), DONE(2), UNSET(3);

    companion object {
        fun getType(value: Int) = values().first { it.value == value }
    }
}

class ReadingListTypeConverter {
    @TypeConverter
    fun fromReadingListTypeToInt(it: ReadingListType) = it.value

    @TypeConverter
    fun fromIntToReadingListType(it: Int) = ReadingListType.getType(it)
}