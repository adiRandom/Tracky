package com.adi_random.tracky.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.adi_random.tracky.models.GoodreadsBook
import com.adi_random.tracky.ui.main.readingList.ReadingListType

/**
 * Created by Adrian Pascu on 23-Jul-20.
 */

@Dao
interface GoodreadsBookDao {
    @Query("SELECT * FROM GoodreadsBook WHERE owner = :type")
    fun getReadingListAsLiveData(type: ReadingListType): LiveData<List<GoodreadsBook>>

    @Query("SELECT * FROM GoodreadsBook WHERE owner = :type")
    fun getReadingList(type: ReadingListType): List<GoodreadsBook>


    @Insert
    fun addBook(book: GoodreadsBook)

    @Query("DELETE FROM GoodreadsBook")
    fun deleteAll()

    @Query("SELECT * FROM GoodreadsBook WHERE id = :id")
    fun getBook(id: Int): GoodreadsBook?

    @Update
    fun updateBook(book: GoodreadsBook)
}


