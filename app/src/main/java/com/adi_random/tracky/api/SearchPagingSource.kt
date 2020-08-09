package com.adi_random.tracky.api

import androidx.paging.PagingSource
import com.adi_random.tracky.models.GoodreadsBook

//TODO: Change to RemoteMEdaitor
class SearchPagingSource(private val query: String, private val callback: okhttp3.Callback) :
    PagingSource<Int, GoodreadsBook>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GoodreadsBook> {
//        TODO: Test initial value. API expects paging from 1
//        val page = params.key ?: 1
        TODO("Not implemented yet")
    }
}