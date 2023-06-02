package com.example.opsc_poe

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.opsc_poe.databinding.ActivityHomeBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Home_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Hide the action bar
        supportActionBar?.hide()

        //set status bar color
        window.statusBarColor = ContextCompat.getColor(this, R.color.Dark_Green)


        //create local fragment controller
        val fragmentControl = FragmentHandler()

        //set the activity view fragment to be the initial view
         fragmentControl.replaceFragment(home_activity_view_fragment(), R.id.fcFragmentContainer, supportFragmentManager)


        binding.tvUserUsername.text = GlobalClass().user.username

        /*
        val activityLayout = findViewById<LinearLayout>(R.id.activitycontainer)

        val customactivity = CustomActivity(this)
        val tvPrimaryText = customactivity.findViewById<TextView>(R.id.tvPrimaryText)
        tvPrimaryText.text = "I want to die"
        activityLayout.addView(customactivity)

         */



        fun CycleHomeFragmentView () {

            if (binding.tvSectionTitle.text == "Activities") {
                binding.tvSectionTitle.text = "Categories"
                fragmentControl.replaceFragment(
                    home_category_view_fragment(),
                    R.id.fcFragmentContainer,
                    supportFragmentManager
                )
            } else {
                binding.tvSectionTitle.text = "Activities"
                fragmentControl.replaceFragment(
                    home_activity_view_fragment(),
                    R.id.fcFragmentContainer,
                    supportFragmentManager
                )
            }

        }


        binding.imgCycleViewLeft.setOnClickListener {
            CycleHomeFragmentView()
        }

        binding.imgCycleViewRight.setOnClickListener {
            CycleHomeFragmentView()
        }










    }
}