package com.adi_random.tracky.ui.main

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.adi_random.tracky.R
import com.adi_random.tracky.databinding.ActivityMainBinding
import com.adi_random.tracky.init.initOptionMenu


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        //Setup the app bar
        setSupportActionBar(binding.toolbar);


        //Instantiate the fragment
        if (savedInstanceState == null) {
            val fragment = MainFragment.newInstance();
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .commitNow()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return initOptionMenu(menu, this)
    }


}