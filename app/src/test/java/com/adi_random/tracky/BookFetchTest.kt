package com.adi_random.tracky

import com.adi_random.tracky.api.searchBook
import com.adi_random.tracky.models.GoodreadsBook
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import java.io.IOException


/**
 * Created by meadi on 6/27/2020.
 */

class BookFetchTest {
    /**
     * Test if the Tracy API searchBook endpoint returns the expected result and gets parsed correctly
     */

    @Test
    fun bookFetchParseValidation() {
        val query = "Dune";
        searchBook(query, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //Unless GoodReads has problems, the request shouldn't fail
                // Reaching here means the test failed

                assertThat(false, `is`(true))
            }

            override fun onResponse(call: Call, response: Response) {
                val gson = Gson();
                val res = gson.fromJson<Array<GoodreadsBook>>(
                    response.body?.charStream(),
                    Array<GoodreadsBook>
                    ::class.java
                )
                assertThat(res.size, `is`(20))

            }
        })
        Thread.sleep(4000)
    }

    @Test
    fun bookFetchResultValidation() {
        val query = "Dune";
        val expectedResultId = 3634639;
        val expectedBookId = 44767458;
        val expectedBookTitle = "Dune (Dune, #1)"

        searchBook(query, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //Unless GoodReads has problems, the request shouldn't fail
                // Reaching here means the test failed

                assertThat(false, `is`(true))
            }

            override fun onResponse(call: Call, response: Response) {
                val gson = Gson();
                val res = gson.fromJson<Array<GoodreadsBook>>(
                    response.body?.charStream(),
                    Array<GoodreadsBook>
                    ::class.java
                )
                val result = res[0];
                assertThat(result.id, `is`(expectedResultId))
                assertThat(result.best_book.id, `is`(expectedBookId))
                assertThat(result.best_book.title, `is`(expectedBookTitle))
            }

        })
        Thread.sleep(4000)
    }
}