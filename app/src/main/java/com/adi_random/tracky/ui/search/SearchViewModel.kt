package com.adi_random.tracky.ui.search

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adi_random.tracky.api.searchBook
import com.adi_random.tracky.database.Database
import com.adi_random.tracky.models.GoodreadsBook
import com.adi_random.tracky.ui.main.readingList.ReadingListType
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

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
    fun search(query: String?) {
        val searchResults = savedStateHandle.getLiveData<Array<GoodreadsBook>>(SEARCH_RESULTS)
        if (searchResults.value == null)
            viewModelScope.launch {
                withContext(Dispatchers.Default) {
                    //Handle the API response
                    val callback: Callback = object : Callback {
                        override fun onFailure(call: Call, e: IOException) {
//                TODO: Display error message
                        }

                        override fun onResponse(call: Call, response: Response) {
                            //                TODO: Check res status

                            val gson = Gson();
                            val parsedRes = gson.fromJson(
                                response.body?.charStream(),
                                Array<GoodreadsBook>::class.java
                            );



                            searchResults.postValue(parsedRes)
                        }


                    }
                    launch { searchBook(query, callback) }

                }
            }
    }

     fun addToReadingList(ctx: Context, book: GoodreadsBook?) {

         if (book != null) {
             val db = Database.getInstance(ctx)
             //Set the owner
             book.owner = ReadingListType.TO_BE_READ

             viewModelScope.launch {
                 withContext(Dispatchers.IO) {
                     db.goodreadsBookDao().addBook(book)
                 }

             }
         }

    }
}