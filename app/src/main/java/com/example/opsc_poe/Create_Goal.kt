package com.example.opsc_poe

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.NumberPicker.OnValueChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.opsc_poe.databinding.ActivityCreateGoalBinding


class Create_Goal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCreateGoalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val currentMaxGoalIDIndex = intent.getIntExtra("currentMaxGoalIDIndex", 0)//intent.extras.getInt("activityIDIndex")
        val currentMinGoalIDIndex = intent.getIntExtra("currentMinGoalIDIndex", 0)//intent.extras.getInt("activityIDIndex")
        var currentGoalID = 0

        if (currentMaxGoalIDIndex == 0)
        {
            currentGoalID = currentMinGoalIDIndex
        }
        else
        {
            currentGoalID = currentMaxGoalIDIndex
        }

        val currentGoal = GlobalClass.goals[currentGoalID]

        //passed activity

        var activity = Temp_ActivityDataClass()
        for (i in GlobalClass.activities.indices)
        {
            if (GlobalClass.activities[i].maxgoalID == currentGoal.goalID || GlobalClass.activities[i].mingoalID == currentGoal.goalID)
            {
                activity = GlobalClass.activities[i]
            }
        }

        //set activity name
        binding.tvActivity.text = activity.name

        //set status bar color
        window.statusBarColor = ContextCompat.getColor(this, R.color.Dark_Green)

        //hour set up
        binding.npHourGoal.minValue = 1
        binding.npHourGoal.maxValue = 24
        binding.npHourGoal.descendantFocusability = ViewGroup.FOCUS_BLOCK_DESCENDANTS

        val timeFrame = arrayOf<String>("Daily", "Weekly", "Monthly")
        binding.npTimeFrameGoal.minValue = 1
        binding.npTimeFrameGoal.maxValue = 3
        binding.npTimeFrameGoal.displayedValues = timeFrame
        binding.npTimeFrameGoal.descendantFocusability = ViewGroup.FOCUS_BLOCK_DESCENDANTS

        var currentInterval = 0

         when (currentGoal.interval){
             "Daily" -> currentInterval = 1
             "Weekly" -> currentInterval = 2
             "Monthly" -> currentInterval = 3
         }

        binding.npTimeFrameGoal.value = currentInterval

        binding.npTimeFrameGoal.setOnValueChangedListener { picker, oldVal, newVal ->

            when (newVal) {
                1 -> {binding.npHourGoal.maxValue = 24}
                2 -> {binding.npHourGoal.maxValue = 168}
                3 -> { binding.npHourGoal.maxValue = 720}
                else -> {binding.npHourGoal.maxValue = 24}
            }
        }


        binding.npHourGoal.value = currentGoal.amount

        //save goal
        binding.tvSaveButton.setOnClickListener()
        {

            var intervalText = ""
            when (binding.npTimeFrameGoal.value) {
                1 -> {intervalText = "Daily"}
                2 -> {intervalText = "Weekly"}
                3 -> {intervalText = "Monthly"}
                else -> {intervalText = "Daily"}
            }
            currentGoal.interval = intervalText
            currentGoal.amount = binding.npHourGoal.value

            //return user to the home view screen
            var intent = Intent(this, Home_Activity::class.java) //ViewActivity
            startActivity(intent)

            /*
            var goal = Temp_GoalDataClass(
                goalID = GlobalClass.goals.size + 1,
                userID = GlobalClass.user.userID,
                amount = binding.npHourGoal.value,
                interval = binding.npTimeFrameGoal.displayedValues.get(binding.npTimeFrameGoal.value)
            )
            GlobalClass.goals.add(goal)
             */
        }





    }
}