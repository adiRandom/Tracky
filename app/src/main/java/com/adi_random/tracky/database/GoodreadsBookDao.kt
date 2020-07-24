package com.adi_random.tracky.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.adi_random.tracky.models.GoodreadsBook
import com.adi_random.tracky.ui.main.readingList.ReadingListType

/**
 * Created by Adrian Pascu on 23-Jul-20.
 */

@Dao
interface GoodreadsBookDao {
    @Query("SELECT * FROM GoodreadsBook WHERE owner = :type")
    fun getReadingList(type: ReadingListType): LiveData<List<GoodreadsBook>>


    @Insert
    fun addBook(book: GoodreadsBook)
}


