package com.example.opsc_poe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.opsc_poe.databinding.ActivityHelpBinding
import com.example.opsc_poe.databinding.ActivitySettingsViewBinding

class Help : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //Set view binding
        val binding = ActivityHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Hide the action bar
        supportActionBar?.hide()

        //set status bar color
        window.statusBarColor = ContextCompat.getColor(this, R.color.Dark_Green)


        var previousScreen = intent.getStringExtra("previousScreen")

        fun GoBack(previousScreenVar: String)
        {

            when (previousScreenVar)
            {
                "Create_Goal" -> {
                    var returningActivityID = intent.getIntExtra("currentActivityID", 0)

                    //return user to the initial view screen
                    var intent = Intent(this, Create_Goal::class.java)
                    intent.putExtra("currentActivityID", returningActivityID)
                    startActivity(intent)
                }


                "Create_Activity" -> {
                    var returningActivityID = intent.getIntExtra("currentDataID", 0)


                    //return user to the initial view screen
                    var intent = Intent(this, CreateActivity::class.java)
                    intent.putExtra("activityIDIndex", returningActivityID)
                    startActivity(intent)
                }

            }


        }

        binding.tvBackText.setOnClickListener()
        {
            if (previousScreen != null) {
                GoBack(previousScreen)
            }
        }

        binding.imgBackIndicator.setOnClickListener()
        {
            if (previousScreen != null) {
                GoBack(previousScreen)
            }
        }

    }
}