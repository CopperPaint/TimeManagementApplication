package com.example.opsc_poe

import android.content.res.ColorStateList
import android.graphics.Color
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.*

//private var data = GlobalClass

class GoalHourCalculator
{
    var yellow = "#fcef5d"
    var red = "#e81e1e"
    var green = "40bf2a"
    public fun CalculateHours(minID: Int, maxID: Int): Triple<String, String, String>
    {
        var maxgoal = GlobalClass.goals[GetGoalIndex(maxID)]
        var mingoal = GlobalClass.goals[GetGoalIndex(minID)]
        var calcHour: String
        var goalText: String
        var barColour: String

        //check min
        val (minHour, minText) = CheckGoal(mingoal.interval, mingoal.amount)
        if (minText.equals("Overtime")) //if over mingoal hours
        {
            //check max
            val (maxHour, maxText) = CheckGoal(maxgoal.interval, maxgoal.amount)
            if (maxText.equals("Hours to Go!")) //if under maxgoal hours
            {
                calcHour = "✔"
                goalText = "Goal Reached!"
                barColour = green
            }
            else
            {
                calcHour = maxHour
                goalText = maxText
                barColour = red
            }
        }
        else
        {
            calcHour = minHour
            goalText = minText
            barColour = yellow
        }
        return Triple(calcHour, goalText, barColour)
    }


    //method to get the index of the goal using the ID
    public fun GetGoalIndex(id: Int): Int
    {
        var index: Int = -1
        for (i in 0..GlobalClass.goals.size)
        {
            if (GlobalClass.goals[i].goalID == id)
            {
                index = i;
            }
        }
        return index
    }

    //method to get the total hours logged in the specified interval
    public fun GetHours(interval: String): Int
    {
        var total: Int = 0
        if (interval.equals("Daily"))
        {
            for (log in GlobalClass.logs)
            {
                if (log.startDate == LocalDate.now())
                {
                    total = total + log.hours
                }
            }
        }
        else if (interval.equals("Weekly"))
        {
            val weekFields = WeekFields.of(Locale.UK)
            val currentWeek = LocalDate.now().get(weekFields.weekOfWeekBasedYear())
            for (log in GlobalClass.logs)
            {
                val logWeek = log.startDate.get(weekFields.weekOfWeekBasedYear())
                if (logWeek == currentWeek)
                {
                    total = total + log.hours
                }
            }
        }
        else if (interval.equals("Monthly"))
        {
            val currentDate = LocalDate.now()
            for (log in GlobalClass.logs)
            {
                if (log.startDate.month == currentDate.month && log.startDate.year == currentDate.year)
                {
                    total = total + log.hours
                }
            }
        }
        return total
    }


    public fun CheckGoal(interval: String, amount: Int): Triple<String, String, String>
    {
        var hourToGo: String = ""
        var hourText: String = ""
        var barColor: String = ""

        val total = GetHours(interval)
        if (total == amount)
        {
            hourToGo = "✔"
            hourText = "Goal Reached!"
            barColor = green
        }
        else if (total > amount)
        {
            hourToGo = (total - amount).toString()
            hourText = "Overtime"
            barColor = red
        }
        else
        {
            hourToGo = (amount - total).toString()
            hourText = "Hours to Go!"
            barColor = yellow
        }
        return Triple(hourToGo, hourText, barColor)
    }
}




