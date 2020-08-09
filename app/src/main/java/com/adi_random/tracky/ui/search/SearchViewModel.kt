package com.adi_random.tracky.ui.search

import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adi_random.tracky.api.SearchApi
import com.adi_random.tracky.database.Database
import com.adi_random.tracky.models.GoodreadsBook
import com.adi_random.tracky.ui.main.readingList.ReadingListType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
//    private val searchResults: MutableLiveData<Array<GoodreadsBook>> by lazy {
////        MutableLiveData<Array<GoodreadsBook>>();
////    }

    companion object {
        private const val SEARCH_RESULTS = "searchResults"
    }

    fun getSearchResults(): LiveData<Array<GoodreadsBook>> =
        savedStateHandle.getLiveData<Array<GoodreadsBook>>(SEARCH_RESULTS)


    //    TODO: Add pagination
    fun search(query: String?, page: Int, ctx: Context) {
        val searchResults = savedStateHandle.getLiveData<Array<GoodreadsBook>>(SEARCH_RESULTS)
        if (searchResults.value == null) {
            viewModelScope.launch {
                val api = SearchApi.create()

                //TODO: Handle errors and check res status
                val res = api.searchBook(query, page)
                res.forEach {
                    it.canBeAddedToReadingList = ObservableBoolean(true)
                }
                val db = Database.getInstance(ctx)
//                            Check if the book can be added to a reading list and post the search results to the viewModel
                for (book: GoodreadsBook in res) {
                    withContext(Dispatchers.IO) {
                        val dbRes = db.goodreadsBookDao().getBook(book.id)
                        if (dbRes != null)
                            book.canBeAddedToReadingList.set(true)
                    }
                    searchResults.postValue(res.toTypedArray())
                }
            }
        }
    }


    fun addToReadingList(ctx: Context, book: GoodreadsBook?) {

        if (book != null) {
            val db = Database.getInstance(ctx)
            //Set the owner
            book.owner = ReadingListType.TO_BE_READ
            book.canBeAddedToReadingList.set(false)

            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    db.goodreadsBookDao().addBook(book)
                }

            }
        }

    }
}