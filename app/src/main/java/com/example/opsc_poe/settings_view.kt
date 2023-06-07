package com.example.opsc_poe

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
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

        //get the extra
        var previousScreen = intent.getStringExtra("previousScreen")


        fun SignOut()
        {
            //return user to the initial view screen
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        fun GoBack(previousScreenVar: String)
        {

            when (previousScreenVar)
            {
                "Home_View" -> {
                    //return user to the initial view screen
                    var intent = Intent(this, Home_Activity::class.java)
                    startActivity(intent)
                }
                "Category_View" -> {
                    var returningCategoryID = intent.getIntExtra("currentDataID", 0)

                    //GlobalClass.InformUser("", returningCategoryID.toString(), this)

                    //return user to the initial view screen
                    var intent = Intent(this, CategoryName::class.java)
                    intent.putExtra("categoryIDIndex", returningCategoryID)
                    startActivity(intent)
                }
                "Activity_View" -> {
                    var returningActivityID = intent.getIntExtra("currentDataID", 0)

                    //GlobalClass.InformUser("", returningActivityID.toString(), this)

                    //return user to the initial view screen
                    var intent = Intent(this, ViewActivity::class.java)
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

        binding.tvViewLogsText.setOnClickListener()
        {
            var intent = Intent(this, Global_Logs::class.java)
            startActivity(intent)
        }

        binding.imgViewLogsIndicator.setOnClickListener()
        {
            var intent = Intent(this, Global_Logs::class.java)
            startActivity(intent)
        }


        binding.tvSignOutText.setOnClickListener()
        {
            SignOut()
        }
        binding.imgSignOutIndicator.setOnClickListener()
        {
            SignOut()
        }


    }
}