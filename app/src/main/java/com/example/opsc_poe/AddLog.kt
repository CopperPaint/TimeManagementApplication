package com.example.opsc_poe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.opsc_poe.databinding.ActivityAddLogBinding
import com.example.opsc_poe.databinding.ActivityCreateCategoryBinding
import java.time.LocalDate

class AddLog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddLogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //passed activity
        var activity = GlobalClass.activities[3]

        //show activity name
        binding.tvActivityName.text = activity.name

        //save goal
        binding.btnAdd.setOnClickListener()
        {
            var log = Temp_LogDataClass(
                logID = GlobalClass.logs.size + 1,
                activityID = activity.activityID,
                userID = GlobalClass.user.userID,
                startDate = LocalDate.of(
                    binding.dpStartDate.year,
                    binding.dpStartDate.month,
                    binding.dpStartDate.dayOfMonth),
                endDate = LocalDate.now(),
                hours = binding.etHours.text.toString().toInt()
            )
            GlobalClass.logs.add(log)
        }
    }
}