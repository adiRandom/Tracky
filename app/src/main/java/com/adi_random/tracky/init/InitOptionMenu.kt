package com.adi_random.tracky.init

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.adi_random.tracky.R
import com.adi_random.tracky.ui.search.SearchActivity

/**
 * Created by Adrian Pascu on 7/5/2020.
 */

fun initOptionMenu(menu: Menu?, context: AppCompatActivity): Boolean {
    val inflater = context.menuInflater;
    inflater.inflate(R.menu.app_bar_menu, menu)

    // Get the SearchView and set the searchable configuration
    val searchManager = context.getSystemService(Context.SEARCH_SERVICE) as SearchManager
    (menu?.findItem(R.id.app_bar_search)?.actionView as SearchView).apply {
        // Assumes current activity is the searchable activity
        setSearchableInfo(
            searchManager.getSearchableInfo(
                ComponentName(
                    context,
                    SearchActivity::class.java
                )
            )
        )
        setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
    }

    return true;
}