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
        val binding = ActivityCategoryNameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //passed category
        var category = GlobalClass.categories[3]
        GlobalClass.user.userID = category.userID

        //display category name
        binding.tvCategoryName.text = category.name

        //display category description
        binding.txtCategoryDescription.text = category.description

        //display category activites
        val activityLayout = findViewById<LinearLayout>(R.id.llactivitycontainer)

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

                    var currentMaxGoal = -1
                    var currentMinGoal = -1

                    for (j in GlobalClass.goals.indices)
                    {
                        if (GlobalClass.activities[i].maxgoalID == GlobalClass.goals[j].goalID)
                        {
                            currentMaxGoal = j
                        }

                        if (GlobalClass.activities[i].mingoalID == GlobalClass.goals[j].goalID)
                        {
                            currentMinGoal = j
                        }
                    }
                    if (currentMinGoal != -1)
                    {
                        if (currentMaxGoal != -1) //both goals
                        {
                            var (hour, text, color) = GoalHourCalculator().CalculateHours(currentMinGoal, currentMaxGoal, GlobalClass.activities[i].activityID)
                            val barColor = ColorStateList.valueOf(Color.parseColor(color))
                            newActivity.binding.vwBar.backgroundTintList = barColor
                            newActivity.binding.tvBlockText.text = text
                            newActivity.binding.tvBlockX.text = hour
                        }
                        else //min only
                        {
                            var goal = GlobalClass.goals[currentMinGoal]
                            var (hour, text, color) = GoalHourCalculator().CheckGoal(goal.interval, goal.amount, GlobalClass.activities[i].activityID)
                            val barColor = ColorStateList.valueOf(Color.parseColor(color))
                            newActivity.binding.vwBar.backgroundTintList = barColor
                            newActivity.binding.tvBlockText.text = text
                            newActivity.binding.tvBlockX.text = hour
                        }
                    }
                    else
                    {
                        if (currentMaxGoal != -1) //max only
                        {
                            var goal = GlobalClass.goals[currentMaxGoal]
                            var (hour, text, color) = GoalHourCalculator().CheckGoal(goal.interval, goal.amount, GlobalClass.activities[i].activityID)
                            val barColor = ColorStateList.valueOf(Color.parseColor(color))
                            newActivity.binding.vwBar.backgroundTintList = barColor
                            newActivity.binding.tvBlockText.text = text
                            newActivity.binding.tvBlockX.text = hour
                        }
                        else //no goals
                        {
                            var total = 0
                            for (k in GlobalClass.logs.indices)
                            {
                                if (GlobalClass.logs[k].activityID == GlobalClass.activities[i].activityID)
                                {
                                    total = total + GlobalClass.logs[k].hours
                                }
                            }
                            newActivity.binding.tvBlockText.text = "Total Hours:"
                            newActivity.binding.tvBlockX.text = total.toString()
                        }
                    }








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
