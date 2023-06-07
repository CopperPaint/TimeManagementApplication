package com.example.opsc_poe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.opsc_poe.databinding.ActivityHelpBinding

class Help : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        //Set view binding
        val binding = ActivityHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Hide the action bar
        supportActionBar?.hide()

        //set status bar color
        window.statusBarColor = ContextCompat.getColor(this, R.color.Dark_Green)

        //get previous screen
        var previousScreen = intent.getStringExtra("previousScreen")

        //messages for help
        when (previousScreen)
        {
            "Create_Goal" -> {
                binding.tvHelpName.text = getString(R.string.CreateGoalHelpHeading)
               binding.tvHelpMessage.text = getString(R.string.CreateGoalHelp)
            }
            "Create_Activity" -> {
                binding.tvHelpName.text = getString(R.string.CreateActivityHelpHeading)
                binding.tvHelpMessage.text = getString(R.string.CreateActivityHelp)
            }
            "Sign_Up" -> {
                //return user to the initial view screen
                binding.tvHelpName.text = getString(R.string.SignUpHelpHeading)
                binding.tvHelpMessage.text = getString(R.string.SignUpHelp)
            }

        }

        //method to go back
        fun GoBack(previousScreenVar: String)
        {
            try
            {
                when (previousScreenVar)
                {
                    "Create_Goal" -> {
                        var returningActivityID = intent.getIntExtra("CurrentActivity", 0)
                        var returningGoalID = intent.getIntExtra("currentGoalIDIndex", 0)

                        //return user to the initial view screen
                        var intent = Intent(this, Create_Goal::class.java)
                        //GlobalClass.InformUser("on Help", returningActivityID.toString(), this)
                        intent.putExtra("CurrentActivity", returningActivityID)
                        intent.putExtra("currentGoalIDIndex", returningGoalID)
                        startActivity(intent)
                    }
                    "Create_Activity" -> {
                        var returningActivityID = intent.getIntExtra("activityIDIndex", 0)

                        //return user to the initial view screen
                        var intent = Intent(this, CreateActivity::class.java)
                        intent.putExtra("activityIDIndex", returningActivityID)
                        startActivity(intent)
                    }
                    "Sign_Up" -> {
                        //return user to the initial view screen
                        var intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("LoadSignUp", true)
                        startActivity(intent)
                    }

                }
            }
            catch (e: Error)
            {
                GlobalClass.InformUser("Error", e.toString(), this)
                //return user to the sign in screen
                var intent = Intent(this, MainActivity::class.java) //ViewActivity
                startActivity(intent)
            }
        }

        //back button
        binding.tvBackText.setOnClickListener()
        {
            try
            {
                if (previousScreen != null)
                {
                    GoBack(previousScreen)
                }
            }
            catch (e: Error)
            {
                GlobalClass.InformUser("Error", e.toString(), this)
                //return user to the sign in screen
                var intent = Intent(this, MainActivity::class.java) //ViewActivity
                startActivity(intent)
            }
        }

        //back image
        binding.imgBackIndicator.setOnClickListener()
        {
            try
            {
                if (previousScreen != null)
                {
                    GoBack(previousScreen)
                }
            }
            catch (e: Error)
            {
                GlobalClass.InformUser("Error", e.toString(), this)
                //return user to the sign in screen
                var intent = Intent(this, MainActivity::class.java) //ViewActivity
                startActivity(intent)
            }
        }
    }
    override fun onBackPressed() {}
}