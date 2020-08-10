package com.adi_random.tracky.ui.search

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.adi_random.tracky.api.SearchApi
import com.adi_random.tracky.api.SearchPagingSource
import com.adi_random.tracky.database.Database
import com.adi_random.tracky.models.GoodreadsBook
import com.adi_random.tracky.ui.main.readingList.ReadingListType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class SearchViewModel(
    private val app: Application
) : AndroidViewModel(app) {

    val searchResults: LiveData<PagingData<GoodreadsBook>> by lazy {
        Pager(PagingConfig(pageSize = 20)) {
            val db = Database.getInstance(app.applicationContext)
            SearchPagingSource(query, SearchApi.create(), db)
        }.liveData
    }

    lateinit var query: String


    fun addToReadingList(ctx: Context, book: GoodreadsBook?) {

        if (book != null) {
            val db = Database.getInstance(ctx)
            //Set the owner and date
            book.owner = ReadingListType.TO_BE_READ
            book.addedAt = Date()
            book.canBeAddedToReadingList.set(false)

            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    db.goodreadsBookDao().addBook(book)
                }

            }
        }

    }
}