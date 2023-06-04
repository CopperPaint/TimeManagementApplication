package com.example.opsc_poe

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.example.opsc_poe.databinding.ActivityCreateCategoryBinding
import com.example.opsc_poe.databinding.ActivityViewBinding

class ViewActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //passed activity
        var activity = GlobalClass.activities[2]
        var catIndex = Temp_CategoryDataClass().GetIndex(activity.categoryID, GlobalClass.categories)
        var category = GlobalClass.categories[catIndex]
        val catColour = ColorStateList.valueOf(Color.parseColor(category.colour))

        val goalContainer = findViewById<LinearLayout>(R.id.llgoalcontainer)
        /*
        //MAX ---------------------------------------------------------------------------
        //get max index
        var maxIndex = GoalHourCalculator().GetGoalIndex(activity.maxgoalID)
        //set max goal
        var maxGoal = GlobalClass.goals[maxIndex]
        //create custom goal
        var customMaxGoal = CustomActivity(this)
        //set primary text
        customMaxGoal.binding.tvPrimaryText.text = "Maximum Goal"
        //set secondary text
        customMaxGoal.binding.tvSecondaryText.text = maxGoal.interval
        //set block color
        customMaxGoal.binding.vwBarBacking.backgroundTintList = catColour
        //get max goal data
        var (maxHour, maxText, maxColor) = GoalHourCalculator().CheckGoal(maxGoal.interval, maxGoal.amount)
        //set bar color
        val maxbarColor = ColorStateList.valueOf(Color.parseColor(maxColor))
        customMaxGoal.binding.vwBar.backgroundTintList = maxbarColor
        //set block text
        customMaxGoal.binding.tvBlockText.text = maxText
        //set block hours
        customMaxGoal.binding.tvBlockX.text = maxHour
        //add to container
        goalContainer.addView(customMaxGoal)

        //MIN ---------------------------------------------------------------------------
        //get max index
        var minIndex = GoalHourCalculator().GetGoalIndex(activity.maxgoalID)
        //set max goal
        var minGoal = GlobalClass.goals[minIndex]
        //create custom goal
        var customMinGoal = CustomActivity(this)
        //set primary text
        customMinGoal.binding.tvPrimaryText.text = "Minimum Goal"
        //set secondary text
        customMinGoal.binding.tvSecondaryText.text = minGoal.interval
        //set block color
        customMinGoal.binding.vwBarBacking.backgroundTintList = catColour
        //get max goal data
        var (minHour, minText, minColor) = GoalHourCalculator().CheckGoal(minGoal.interval, minGoal.amount)
        //set bar color
        val minbarColor = ColorStateList.valueOf(Color.parseColor(minColor))
        customMinGoal.binding.vwBar.backgroundTintList = minbarColor
        //set block text
        customMinGoal.binding.tvBlockText.text = minText
        //set block hours
        customMinGoal.binding.tvBlockX.text = minHour
        //add to container
        goalContainer.addView(customMinGoal)

         */


        //edit activity
        binding.btnEditActivity.setOnClickListener()
        {
            //send activity data to edit page
        }

        //add log
        binding.btnAddLog.setOnClickListener()
        {
            //add log to activity
        }







    }
}