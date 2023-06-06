package com.example.opsc_poe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.opsc_poe.databinding.ActivitySettingsViewBinding

class Help : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Set view binding
        val binding = ActivitySettingsViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var previousScreen = intent.getStringExtra("previousScreen")

        fun GoBack(previousScreenVar: String)
        {

            when (previousScreenVar)
            {
                "Create_Goal" -> {
                    var returningCategoryID = intent.getIntExtra("currentDataID", 0)


                    //return user to the initial view screen
                    var intent = Intent(this, Create_Goal::class.java)
                    intent.putExtra("categoryIDIndex", returningCategoryID)
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

    }
}