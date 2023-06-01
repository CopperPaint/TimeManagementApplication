package com.example.opsc_poe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

class ViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        val goalContainer = findViewById<LinearLayout>(R.id.llgoalcontainer)

        val customMaxGoal = CustomActivity(this)
        val tvPrimaryText = customMaxGoal.findViewById<TextView>(R.id.tvPrimaryText)
        tvPrimaryText.text = "Maximum Goal"
        goalContainer.addView(customMaxGoal)






    }
}