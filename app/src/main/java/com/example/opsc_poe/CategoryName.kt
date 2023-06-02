package com.example.opsc_poe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.example.opsc_poe.databinding.HomeActivityViewFragmentBinding

class CategoryName : AppCompatActivity() {
    private var _binding: HomeActivityViewFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_name)

        //global data
        var globaldata = GlobalClass()

        //passed category
        var category = Temp_CategoryDataClass()


        //display category name
        val CategoryName = findViewById<TextView>(R.id.tvCategoryName)
        CategoryName.text = category.name

        //display category descriptioin
        val CategoryDescription = findViewById<TextView>(R.id.txtCategoryDescription)
        CategoryDescription.text = category.description


        //display category activites
        val activityLayout = findViewById<LinearLayout>(R.id.llactivitycontainer)
        val goalCalculator = GoalHourCalculator() //goal calculator

        for (i in globaldata.activities)
        {
            if (i.categoryID == category.categoryID)
            {
                //create new views
                var newActivity = CustomActivity(this)

                //set primary text
                newActivity.binding.tvPrimaryText.text = i.name

                //set secondary text (if second line is not needed then set to null
                newActivity.binding.tvSecondaryText.text = null

                //set the color of the divider bar between the text and the activity color shape
                newActivity.binding.vwBar.backgroundTintList = ContextCompat.getColorStateList(this, R.color.Light_Green)

                //set the activity color shape color
                //newActivity.binding.llBlockText.backgroundTintList = ContextCompat.getColorStateList(this, category.colour)

                //calculate block time and text
                val (hour, text) = goalCalculator.CalculateHours(i.mingoalID, i.maxgoalID)

                //set the activity color block text
                newActivity.binding.tvBlockText.text = text

                //set the activity color block time
                newActivity.binding.tvBlockX.text = hour

                //add the new view
                activityLayout.addView(newActivity)

            }





        }










        val addactivityButton = findViewById<Button>(R.id.btnAddActvity)
        addactivityButton?.setOnClickListener()
        {
            //go to add actvity page
        }

        val editCategory = findViewById<Button>(R.id.btnEditCategory)
        addactivityButton?.setOnClickListener()
        {
            //go to add actvity page
        }



    }
}
