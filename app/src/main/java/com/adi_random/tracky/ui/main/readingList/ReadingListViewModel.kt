package com.adi_random.tracky.ui.main.readingList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adi_random.tracky.models.GoodreadsBook

class ReadingListViewModel : ViewModel() {
    private val readingList: MutableLiveData<Array<GoodreadsBook>> by lazy {
        MutableLiveData<Array<GoodreadsBook>>()
    }

    var type: ReadingListType = ReadingListType.UNSET


    fun getReadingList(): LiveData<Array<GoodreadsBook>> = readingList;
}

enum class ReadingListType(val value: Int) {
    TO_BE_READ(0), READING(1), DONE(2), UNSET(3);

    companion object {
        fun getType(value: Int) = ReadingListType.values().first { it.value == value }
    }
}