package com.adi_random.tracky.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.gildor.coroutines.okhttp.await

/**
 * Created by Adrian Pascu on 06-Jul-20.
 */

val client = OkHttpClient.Builder().build()

suspend fun createBitmapFromUrl(url: String): Bitmap? = withContext(Dispatchers.IO) {
    val req = Request.Builder().url(url).build();
    val res = client.newCall(req).await();
    BitmapFactory.decodeStream(res.body?.byteStream())
}