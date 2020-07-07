package com.adi_random.tracky.ui.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adi_random.tracky.api.searchBook
import com.adi_random.tracky.models.GoodreadsBook
import com.adi_random.tracky.utils.createBitmapFromUrl
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class SearchViewModel() : ViewModel() {
    private val searchResults: MutableLiveData<Array<GoodreadsBook>> by lazy {
        MutableLiveData<Array<GoodreadsBook>>();
    }

    fun getSearchResults(): LiveData<Array<GoodreadsBook>> {
        return searchResults;
    }

    //    TODO: Add pagination
    fun search(query: String?) {
        val callback: Callback = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
//                TODO: Display error message
            }

            override fun onResponse(call: Call, response: Response) {
                //                TODO: Check res status
                GlobalScope.launch {
                    val gson = Gson();
                    val parsedRes = gson.fromJson(
                        response.body?.charStream(),
                        Array<GoodreadsBook>::class.java
                    );
                    // Create the bitmap from the imageUrl
                    launch {
                        for (i in 0 until parsedRes.size) {
                            launch {
                                parsedRes[i].best_book.imageBitmap =
                                    createBitmapFromUrl(parsedRes[i].best_book.image_url)
                            }
                        }
                    }.join()
                    searchResults.postValue(parsedRes)
                }

            }

        }
        searchBook(query, callback)
    }
}