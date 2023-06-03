package com.example.opsc_poe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.opsc_poe.databinding.ActivityAddLogBinding
import com.example.opsc_poe.databinding.ActivityCreateCategoryBinding

class AddLog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddLogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //passed activity
        var activity = Temp_ActivityDataClass()

        //show activity name
        binding.tvActivityName.text = activity.name

        //save goal
        binding.btnAdd.setOnClickListener()
        {
            var log = Temp_LogDataClass()

        }














    }
}