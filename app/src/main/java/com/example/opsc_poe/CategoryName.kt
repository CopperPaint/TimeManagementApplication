package com.example.opsc_poe

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.example.opsc_poe.GlobalClass.Companion.DoubleToTime
import com.example.opsc_poe.databinding.ActivityCategoryNameBinding
import com.example.opsc_poe.databinding.HomeActivityViewFragmentBinding

class CategoryName : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCategoryNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Hide the action bar
        supportActionBar?.hide()

        //set status bar color
        window.statusBarColor = ContextCompat.getColor(this, R.color.Dark_Green)


        val categoryIDIndex = intent.getIntExtra("categoryIDIndex", 0)

        //passed category
        var category = GlobalClass.categories[categoryIDIndex]
        GlobalClass.user.userID = category.userID

        //display category name
        binding.tvCategoryName.text = category.name

        //display category description
        binding.tvDescription.text = category.description


        var userHasData = false
        for (i in GlobalClass.activities.indices) {

            if (GlobalClass.activities[i].categoryID == GlobalClass.categories[categoryIDIndex].categoryID)
            {
                userHasData = true
                break
            }

        }

        if (userHasData == false)
        {
            GlobalClass.NoUserAppData(binding.llBars, this, this, "Activity")
        }
        else {



            //display category activites
            val activityLayout = binding.llBars

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

                        //bold the primary text
                        newActivity.binding.tvPrimaryText.typeface = Typeface.DEFAULT_BOLD

                        //change the position of the primary text on the custom component
                        val activityParam: ViewGroup.MarginLayoutParams = newActivity.binding.tvPrimaryText.layoutParams as ViewGroup.MarginLayoutParams
                        activityParam.setMargins(activityParam.leftMargin, 56, activityParam.rightMargin, activityParam.bottomMargin)
                        newActivity.binding.tvPrimaryText.layoutParams = activityParam

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

                        var mingoal = GlobalClass.goals[currentMinGoal]
                        var maxgoal = GlobalClass.goals[currentMaxGoal]
                        if (mingoal.isSet)
                        {
                            if (maxgoal.isSet) //both goals
                            {
                                var (hour, text, color) = GoalHourCalculator().CalculateHours(currentMinGoal, currentMaxGoal, GlobalClass.activities[i].activityID)
                                val barColor = ColorStateList.valueOf(Color.parseColor(color))
                                newActivity.binding.vwBar.backgroundTintList = barColor
                                newActivity.binding.tvBlockText.text = text
                                newActivity.binding.tvBlockX.text = DoubleToTime(hour)
                            }
                            else //min only
                            {
                                var goal = GlobalClass.goals[currentMinGoal]
                                var (hour, text, color) = GoalHourCalculator().CheckGoal(goal.interval, goal.amount, GlobalClass.activities[i].activityID)
                                val barColor = ColorStateList.valueOf(Color.parseColor(color))
                                newActivity.binding.vwBar.backgroundTintList = barColor
                                newActivity.binding.tvBlockText.text = text
                                newActivity.binding.tvBlockX.text = DoubleToTime(hour)
                            }
                        }
                        else
                        {
                            if (maxgoal.isSet) //max only
                            {
                                var goal = GlobalClass.goals[currentMaxGoal]
                                var (hour, text, color) = GoalHourCalculator().CheckGoal(goal.interval, goal.amount, GlobalClass.activities[i].activityID)
                                val barColor = ColorStateList.valueOf(Color.parseColor(color))
                                newActivity.binding.vwBar.backgroundTintList = barColor
                                newActivity.binding.tvBlockText.text = text
                                newActivity.binding.tvBlockX.text = DoubleToTime(hour)
                            }
                            else //no goals
                            {
                                var total = 0.0
                                for (k in GlobalClass.logs.indices)
                                {
                                    if (GlobalClass.logs[k].activityID == GlobalClass.activities[i].activityID)
                                    {
                                        total = total + GlobalClass.logs[k].hours
                                    }
                                }
                                newActivity.binding.tvBlockText.text = "Total Hours:"
                                newActivity.binding.tvBlockX.text = DoubleToTime(total.toString())
                            }
                        }



                        //set the click function of the activity to load the activity detail view
                        newActivity.setOnClickListener(){
                            var intent = Intent(this, ViewActivity::class.java)
                            intent.putExtra("activityIDIndex", i)
                            startActivity(intent)
                        }



                        //add the new view
                        activityLayout.addView(newActivity)
                    }
                }
            }

        }




        //add activity to category
        binding.imgAddActivity.setOnClickListener()
        {
            //go to add actvity page
            var intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }

        //Edit Category
        binding.imgEditCategory.setOnClickListener()
        {
            //go to add create category page
            var intent = Intent(this, CreateCategory::class.java)
            intent.putExtra("categoryIDIndex", categoryIDIndex)
            startActivity(intent)
        }


        binding.imgBackIndicator.setOnClickListener()
        {
            //return user back to home view
            var intent = Intent(this, Home_Activity::class.java)
            startActivity(intent)
        }

        binding.imgSettingsButton.setOnClickListener()
        {
            var intent = Intent(this, settings_view::class.java)
            intent.putExtra("previousScreen", "Category_View")
            intent.putExtra("currentDataID", categoryIDIndex)
            startActivity(intent)
        }
    }
}
