package com.adi_random.tracky

import androidx.lifecycle.ViewModelProvider
import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import com.adi_random.tracky.api.searchBook
import com.adi_random.tracky.database.Database
import com.adi_random.tracky.models.GoodreadsBook
import com.adi_random.tracky.ui.main.MainActivity
import com.adi_random.tracky.ui.main.readingList.ReadingListType
import com.adi_random.tracky.ui.search.SearchViewModel
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Test
import java.io.IOException

/**
 * Created by Adrian Pascu on 25-Jul-20.
 */

@MediumTest
class ReadingListTest {


    companion object {
        private lateinit var db: Database._Database

        @BeforeClass
        @JvmStatic
        fun getDatabaseInstance() {
            db = Database.getInstance(activity.activity.applicationContext)
            db.goodreadsBookDao().deleteAll()
        }

        @get:ClassRule
        @JvmStatic
        public var activity = ActivityTestRule(MainActivity::class.java)

    }


    @Test
    fun addToReadingList() {
        val callback = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val gson = Gson()

                val res = gson.fromJson<Array<GoodreadsBook>>(
                    response.body?.charStream(),
                    Array<GoodreadsBook>
                    ::class.java
                )
                var viewModel: SearchViewModel =
                    ViewModelProvider(activity.activity).get(SearchViewModel::class.java)
                viewModel.addToReadingList(activity.activity.applicationContext, res[0])

            }
        }
        GlobalScope.launch { searchBook("Dune", callback) }
        Thread.sleep(3000L)

        val dbRes = db.goodreadsBookDao().getReadingList(ReadingListType.TO_BE_READ)
        assertThat("Not empty array", dbRes, not(empty<GoodreadsBook>()))
        assertThat("Explicit size check", dbRes.size, `is`(1))

    }
}