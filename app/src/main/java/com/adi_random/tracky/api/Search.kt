package com.adi_random.tracky.api

import com.adi_random.tracky.models.GoodreadsBook
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Adrian Pascu on 6/27/2020.
 */


interface SearchApi {
    @GET("searchBook")
    suspend fun searchBook(
        @Query("q") query: String?,
        @Query("page") page: Int
    ): List<GoodreadsBook>

    companion object {
        fun create(): SearchApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(SEARCH_BOOK_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(SearchApi::class.java)
        }
    }
}