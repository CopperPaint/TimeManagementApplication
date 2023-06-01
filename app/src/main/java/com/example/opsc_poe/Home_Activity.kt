package com.example.opsc_poe

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import com.example.opsc_poe.databinding.ActivityHomeBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Home_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_home)

        val activityLayout = findViewById<LinearLayout>(R.id.activitycontainer)


        val customactivity = CustomActivity(this)
        val tvPrimaryText = customactivity.findViewById<TextView>(R.id.tvPrimaryText)
        activityLayout.addView(customactivity)

        val customactivity2 = CustomActivity(this)
        activityLayout.addView(customactivity2)


        /*
        val db = Firebase.firestore
        db.collection("Activity")
            .get()
            .addOnSuccessListener { result ->
                for (document in result)
                {
                    var name: String = document.data.getValue("Name").toString()
                    val doccustomactivity = CustomActivity(this)
                    val tvPrimaryText = doccustomactivity.findViewById<TextView>(R.id.tvPrimaryText)
                    tvPrimaryText.text = name
                    activityLayout.addView(doccustomactivity)

                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
        */
    }
}