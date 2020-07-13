package com.adi_random.tracky.ui.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.adi_random.tracky.R
import com.adi_random.tracky.databinding.SearchActivityBinding
import com.adi_random.tracky.init.initOptionMenu

class SearchActivity : AppCompatActivity() {

    private lateinit var viewBinding: SearchActivityBinding
    private var query: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = SearchActivityBinding.inflate(layoutInflater)
        val root = viewBinding.root
        setContentView(root)


        // Setup app bar

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.search_app_bar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Get the query string
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY).also {

                //Add the query to the appbar
                query = it
                updateAppBarQuery(it)
            }
        }

        //Instantiate the fragment
        if (savedInstanceState == null) {
            val fragment = SearchFragment.newInstance();
            val bundle = Bundle();
            bundle.putString(Intent.ACTION_SEARCH, query)
            fragment.arguments = bundle;
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
//                .addToBackStack(null)
                .commitNow()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return initOptionMenu(menu, this)
    }


    private fun updateAppBarQuery(q: String?) {
        supportActionBar?.customView?.findViewById<TextView>(R.id.query)?.apply {
            text = q
        }
    }


}