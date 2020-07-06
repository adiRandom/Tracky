package com.adi_random.tracky

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
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

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return initOptionMenu(menu, this)
    }

}