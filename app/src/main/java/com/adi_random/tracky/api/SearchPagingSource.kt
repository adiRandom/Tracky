package com.adi_random.tracky.api

import androidx.databinding.ObservableBoolean
import androidx.paging.PagingSource
import com.adi_random.tracky.database.Database
import com.adi_random.tracky.models.GoodreadsBook
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//TODO: Change to RemoteMEdaitor
class SearchPagingSource(
    private val query: String,
    private val api: SearchApi,
    private val db: Database.AppDatabase
) :
    PagingSource<Int, GoodreadsBook>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GoodreadsBook> =
        withContext(Dispatchers.IO) {
//        TODO: Test initial value. API expects paging from 1
            val page = params.key ?: 1
            try {
                val res = api.searchBook(query, page).execute()
                if (res.code() == 200) {
                    val data = res.body()
                    if (data != null) {
                        data.forEach {
                            it.canBeAddedToReadingList = ObservableBoolean(true)
                        }
//                            Check if the book can be added to a reading list and post the search results to the viewModel
                        launch {
                            for (book: GoodreadsBook in data) {
                                lookupDbForAddingToReadingList(book, db)
//                        TODO:Update viewmodel
//                        searchResults.postValue(data.toTypedArray())
                            }
                        }.join()
                        LoadResult.Page(data, null, page + 1)
                    } else {
                        LoadResult.Error<Int, GoodreadsBook>(Error())
                    }
                } else {
//                    TODO: Display error message
//                    TODO: Check if code 500 ends here or in catch block
                    LoadResult.Error<Int, GoodreadsBook>(Error())
                }
            } catch (e: Error) {
//                    TODO: Display error message
                LoadResult.Error<Int, GoodreadsBook>(e)
            }
        }
}

private suspend fun lookupDbForAddingToReadingList(book: GoodreadsBook, db: Database.AppDatabase) =
    withContext(Dispatchers.IO) {
        val dbRes = db.goodreadsBookDao().getBook(book.id)
        if (dbRes != null)
            book.canBeAddedToReadingList.set(true)
    }
