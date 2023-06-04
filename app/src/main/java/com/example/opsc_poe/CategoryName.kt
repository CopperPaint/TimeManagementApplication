package com.example.opsc_poe

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.example.opsc_poe.databinding.ActivityCategoryNameBinding
import com.example.opsc_poe.databinding.HomeActivityViewFragmentBinding

class CategoryName : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_name)

        val binding = ActivityCategoryNameBinding.inflate(layoutInflater)
        supportActionBar?.hide()

        //passed category
        var category = GlobalClass.categories[3]
        GlobalClass.user.userID = category.userID

        //display category name
        binding.tvCategoryName.text = GlobalClass.categories[3].name

        //display category descriptioin
        binding.txtCategoryDescription.text = GlobalClass.categories[3].description

        //display category activites
        val activityLayout = findViewById<LinearLayout>(R.id.llactivitycontainer)
        val goalCalculator = GoalHourCalculator() //goal calculator

        //loop through activites
        for (i in GlobalClass.activities.indices)
        {
            //if activity belongs to user
            if (GlobalClass.user.userID == GlobalClass.activities[i].userID)
            {
                //if activity belongs to category
                if (GlobalClass.activities[i].categoryID == category.categoryID)
                {
                    //create new custom activity
                    var newActivity = CustomActivity(this)
                    //set primary text
                    newActivity.binding.tvPrimaryText.text = GlobalClass.activities[i].name

                    //set secondary text - set to null
                    newActivity.binding.tvSecondaryText.text = null

                    //set the activity color shape color
                    val catColour = ColorStateList.valueOf(Color.parseColor(category.colour))
                    newActivity.binding.llBlockText.backgroundTintList = catColour

                    var currentMaxGoal = 0
                    var currentMinGoal = 0

                    for (j in GlobalClass.goals.indices)
                    {
                        if (GlobalClass.activities[i].maxgoalID == GlobalClass.goals[j].goalID)
                        {
                            currentMaxGoal = GlobalClass.goals[i].goalID
                        }

                        if (GlobalClass.activities[i].mingoalID == GlobalClass.goals[j].goalID)
                        {
                            currentMinGoal = GlobalClass.goals[i].goalID
                        }
                    }
                    var (hour, text, color) = GoalHourCalculator().CalculateHours(currentMinGoal, currentMaxGoal)

                    val barColor = ColorStateList.valueOf(Color.parseColor(color))
                    newActivity.binding.vwBar.backgroundTintList = barColor
                    newActivity.binding.tvBlockText.text = text
                    newActivity.binding.tvBlockX.text = hour

                    //add the new view
                    activityLayout.addView(newActivity)
                }
            }
        }

        //add activity to category
        binding.btnAddActvity.setOnClickListener()
        {
            //go to add actvity page
        }

        //Edit Category
        binding.btnEditCategory.setOnClickListener()
        {
            //go to add actvity page
        }
    }
}
