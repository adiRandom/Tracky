package com.adi_random.tracky.ui.main.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adi_random.tracky.R
import com.adi_random.tracky.models.GoodreadsBook
import com.google.gson.Gson
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.io.IOException

class SearchFragment : Fragment() {


    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val query = arguments?.getString(Intent.ACTION_SEARCH);
        queryApi(query)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    //    TODO:Implement pagination
    private fun queryApi(q: String?) {
        //Create a http request
        val client = OkHttpClient();
        val url = getString(R.string.search_book_api)
        val urlBuilder = url.toHttpUrlOrNull()?.newBuilder();
        val encodedUrl = urlBuilder?.addQueryParameter("q", q)?.build();
        if (encodedUrl != null) {
            val req = Request.Builder().url(encodedUrl).build()
            client.newCall(req).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
//                    TODO: Throw error and display error message
                }

                override fun onResponse(call: Call, response: Response) {
                    val gson = Gson();
                    val books = gson.fromJson<Array<GoodreadsBook>>(
                        response.body?.charStream(),
                        Array<GoodreadsBook>
                        ::class.java
                    )
//                    TODO: Add books to viewmodel
                }

            })
        } else {
//           TODO: Throw error and display error message
        }
    }

}