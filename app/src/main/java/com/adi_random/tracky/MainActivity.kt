package com.adi_random.tracky

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.adi_random.tracky.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        //Setup the app bar
        setSupportActionBar(binding.toolbar);

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater;
        inflater.inflate(R.menu.app_bar_menu, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_search -> {
            onSearchRequested()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}