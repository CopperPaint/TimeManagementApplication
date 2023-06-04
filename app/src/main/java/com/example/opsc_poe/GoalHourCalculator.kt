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
    var green = "#40bf2a"
    public fun CalculateHours(minIndex: Int, maxIndex: Int, activityID: Int): Triple<String, String, String>
    {
        var mingoal = GlobalClass.goals[minIndex]
        var maxgoal = GlobalClass.goals[maxIndex]
        var calcHour: String
        var goalText: String
        var barColour: String

        //check min
        val (minHour, minText, minColor) = CheckGoal(mingoal.interval, mingoal.amount, activityID)
        if (minText.equals("Overtime")) //if over mingoal hours
        {
            //check max
            val (maxHour, maxText, maxColor) = CheckGoal(maxgoal.interval, maxgoal.amount, activityID) //here
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
                barColour = maxColor
            }
        }
        else
        {
            calcHour = minHour
            goalText = minText
            barColour = minColor
        }

        //test area
        //barColour = "#a0c064"
        return Triple(calcHour, goalText, barColour)
    }


    //method to get the index of the goal using the ID
    public fun GetGoalIndex(id: Int): Int
    {
        var index: Int = -1
        for (i in 0..GlobalClass.goals.size-1)//added -1
        {
            if (GlobalClass.goals[i].goalID == id)
            {
                index = i;
            }
        }
        return index
    }

    //method to get the total hours logged in the specified interval
    public fun GetHours(interval: String, activityID: Int): Int
    {
        var total: Int = 0
        if (interval.equals("Daily"))
        {
            for (log in GlobalClass.logs.indices)
            {
                if (GlobalClass.logs[log].activityID == activityID) //this if statement is wrong //log activity id is wrong  //3 must be changed back to activityID
                {
                    if (GlobalClass.logs[log].startDate == LocalDate.now()) //ask what this does
                    {
                        total = total + GlobalClass.logs[log].hours
                    }
                }
            }
        }
        else if (interval.equals("Weekly"))
        {
            val weekFields = WeekFields.of(Locale.UK)
            val currentWeek = LocalDate.now().get(weekFields.weekOfWeekBasedYear())
            for (log in GlobalClass.logs.indices)
            {
                if (GlobalClass.logs[log].activityID == activityID)
                {
                    val logWeek = GlobalClass.logs[log].startDate.get(weekFields.weekOfWeekBasedYear())
                    if (logWeek == currentWeek)
                    {
                        total = total + GlobalClass.logs[log].hours
                    }
                }
            }
        }
        else if (interval.equals("Monthly"))
        {
            val currentDate = LocalDate.now()

            for (log in GlobalClass.logs.indices)
            {
                if (GlobalClass.logs[log].activityID == activityID) //this if statement is wrong //log activity id is wrong  //3 must be changed back to activityID
                {
                    if (GlobalClass.logs[log].startDate.month == currentDate.month && GlobalClass.logs[log].startDate.year == currentDate.year)
                    {
                        total = total + GlobalClass.logs[log].hours
                    }
                }
            }
        }
        return total
    }


    public fun CheckGoal(interval: String, amount: Int, activityID: Int): Triple<String, String, String>
    {
        var hourToGo: String = ""
        var hourText: String = ""
        var barColor: String = ""

        val total = GetHours(interval, activityID)
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




