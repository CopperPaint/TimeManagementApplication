package com.example.opsc_poe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.opsc_poe.databinding.ActivityHomeBinding
import com.example.opsc_poe.databinding.ActivityMainBinding

class Home_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //setContentView(R.layout.activity_home)
    }
}