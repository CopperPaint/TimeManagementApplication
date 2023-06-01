package com.example.opsc_poe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class CategoryName : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_name)

        val CategoryName = findViewById<TextView>(R.id.tvCategoryName)
        CategoryName.text = "set cat name"

        val CategoryDescription = findViewById<TextView>(R.id.txtCategoryDescription)
        CategoryDescription.text = "Insert cat description"




        val activityLayout = findViewById<LinearLayout>(R.id.llactivitycontainer)

        //for each activity in category
        val customactivity = CustomActivity(this)

        //set activity name
        val PrimaryText = customactivity.findViewById<TextView>(R.id.tvPrimaryText)
        PrimaryText.text = "set activity name"

        //leave secondary blank
        val SecondayText = customactivity.findViewById<TextView>(R.id.tvSecondaryText)
        SecondayText.text = ""

        //display hours until goal
        val Hour = customactivity.findViewById<TextView>(R.id.tvBlockX)
        //Hour.text = "insert amount of hours until goal"
        //if goal reached
        Hour.text = "✔"

        //set block colour to category colour
        val blockcolour = customactivity.findViewById<View>(R.id.vBlock)
        //leave alpha as 255
        blockcolour.setBackgroundColor(Color.argb(255,90,255,200))

        //add to view
        activityLayout.addView(customactivity)


        val addactivityButton = findViewById<Button>(R.id.btnAddActvity)
        addactivityButton?.setOnClickListener()
        {
            //go to add actvity page
        }

        val editCategory = findViewById<Button>(R.id.btnEditCategory)
        addactivityButton?.setOnClickListener()
        {
            //go to add edit category page
        }

    }
}
