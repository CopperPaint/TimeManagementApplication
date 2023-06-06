package com.example.opsc_poe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.opsc_poe.databinding.ActivityMainBinding
import com.example.opsc_poe.databinding.ActivitySettingsViewBinding

class settings_view : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Hide the action bar
        supportActionBar?.hide()

        //set status bar color
        window.statusBarColor = ContextCompat.getColor(this, R.color.Dark_Green)

        //Set view binding
        val binding = ActivitySettingsViewBinding.inflate(layoutInflater)
        setContentView(binding.root)



        fun SignOut()
        {
            //return user to the initial view screen
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}