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
        var activity = GlobalClass.activities[1]
        var catIndex = Temp_CategoryDataClass().GetIndex(activity.categoryID, GlobalClass.categories)
        var category = GlobalClass.categories[catIndex]
        val catColour = ColorStateList.valueOf(Color.parseColor(category.colour))

        binding.tvActivity.text = activity.name
        binding.tvCategory.text = category.name
        binding.tvDescription.text = activity.description


        //get goal indexes
        var currentMaxGoal = -1
        var currentMinGoal = -1

        for (j in GlobalClass.goals.indices)
        {
            if (activity.maxgoalID == GlobalClass.goals[j].goalID)
            {
                currentMaxGoal = j
            }

            if (activity.mingoalID == GlobalClass.goals[j].goalID)
            {
                currentMinGoal = j
            }
        }


        //MAX GOAL
        //------------------------------------------------
        var maxGoalCustom = CustomActivity(this)
        maxGoalCustom.binding.tvPrimaryText.text = "Maximum Goal"
        maxGoalCustom.binding.llBlockText.backgroundTintList = catColour

        if (currentMaxGoal == -1)
        {
            maxGoalCustom.binding.tvSecondaryText.text = "Goal Not Set"
            maxGoalCustom.binding.tvBlockText.text = "Hours"
            maxGoalCustom.binding.tvBlockX.text = "X"
        }
        else
        {
            var maxGoal = GlobalClass.goals[currentMaxGoal]
            maxGoalCustom.binding.tvSecondaryText.text = maxGoal.interval
            var (maxhour, maxText, maxColor) = GoalHourCalculator().CheckGoal(maxGoal.interval, maxGoal.amount, activity.activityID)
            val maxBarColor = ColorStateList.valueOf(Color.parseColor(maxColor))
            maxGoalCustom.binding.vwBar.backgroundTintList = maxBarColor
            maxGoalCustom.binding.tvBlockText.text = maxText
            maxGoalCustom.binding.tvBlockX.text = maxhour

        }
        binding.llgoalcontainer.addView(maxGoalCustom)


        //MIN GOAL
        //------------------------------------------------
        var minGoalCustom = CustomActivity(this)
        minGoalCustom.binding.tvPrimaryText.text = "Minimum Goal"
        minGoalCustom.binding.llBlockText.backgroundTintList = catColour
        if (currentMinGoal == -1)
        {
            minGoalCustom.binding.tvSecondaryText.text = "Goal Not Set"
            minGoalCustom.binding.tvBlockText.text = "Hours"
            minGoalCustom.binding.tvBlockX.text = "X"
        }
        else
        {
            var minGoal = GlobalClass.goals[currentMinGoal]
            minGoalCustom.binding.tvSecondaryText.text = minGoal.interval
            var (hour, text, color) = GoalHourCalculator().CheckGoal(minGoal.interval, minGoal.amount, activity.activityID)
            val maxBarColor = ColorStateList.valueOf(Color.parseColor(color))
            minGoalCustom.binding.vwBar.backgroundTintList = maxBarColor
            minGoalCustom.binding.tvBlockText.text = text
            minGoalCustom.binding.tvBlockX.text = hour
        }
        binding.llgoalcontainer.addView(minGoalCustom)


    }
}