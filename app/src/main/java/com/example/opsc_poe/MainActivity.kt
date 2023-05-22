package com.example.opsc_poe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.marginLeft

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //getActionBar().hide();
        supportActionBar?.hide()

        //PLEASE FUCKING WORK
        //I BEG OF YOU
        //I SWEAR TO GOD


        //testing (please) - jake

        //testing work - ben

        val signInSelect = findViewById<TextView>(R.id.tvSignIn)
        val signUpSelect = findViewById<TextView>(R.id.tvSignUp)
        val EntryMethodSelector = findViewById<View>(R.id.vwEntryMethod)

        signUpSelect.setOnClickListener{

            //EntryMethodSelector.left = 500
            // EntryMethodSelector. = 500
            //move or hide the bar to be on the sign up

        }


        signInSelect.setOnClickListener{

            EntryMethodSelector.left = 100
          //move or hide the bar to be on the sign in
        }

    }
}