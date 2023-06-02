package com.example.opsc_poe

import android.os.Bundle
import android.view.ViewGroup
import android.widget.NumberPicker.OnValueChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.opsc_poe.databinding.ActivityCreateGoalBinding


class Create_Goal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_create_goal)

        val binding = ActivityCreateGoalBinding.inflate(layoutInflater)
       setContentView(binding.root)


        var globalData = GlobalClass()

        //passed activity
        var activity = Temp_ActivityDataClass()
        //set activity name
        binding.tvActivity.text = activity.name


        //Hide the action bar
        supportActionBar?.hide()

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

        binding.npTimeFrameGoal.setOnValueChangedListener { picker, oldVal, newVal ->

            when (newVal) {
                1 -> {binding.npHourGoal.maxValue = 24}
                2 -> {binding.npHourGoal.maxValue = 168}
                3 -> { binding.npHourGoal.maxValue = 720}
                else -> {binding.npHourGoal.maxValue = 24}
            }
        }


        //save goal
        binding.tvSaveButton.setOnClickListener()
        {
            var goal = Temp_GoalDataClass(
                goalID = globalData.goals.size + 1,
                userID = globalData.user.userID,
                amount = binding.npHourGoal.value,
                interval = binding.npTimeFrameGoal.displayedValues.get(binding.npTimeFrameGoal.value)
            )
        }
    }
}