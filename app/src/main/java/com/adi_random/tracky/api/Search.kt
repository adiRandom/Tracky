package com.adi_random.tracky.api

import okhttp3.Callback
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by Adrian Pascu on 6/27/2020.
 */

fun searchBook(query: String?, callback: Callback) {
    val client = OkHttpClient();
    val url = SEARCH_BOOK_ENDPOINT;
    val urlBuilder = url.toHttpUrlOrNull()?.newBuilder();
    val encodedUrl = urlBuilder?.addQueryParameter("q", query)?.build();
    if (encodedUrl != null) {
        val req = Request.Builder().url(encodedUrl).build()
        client.newCall(req).enqueue(callback)

    } else {
        throw KotlinNullPointerException()
    }
}