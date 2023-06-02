package com.example.opsc_poe

import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.*

private var data = GlobalClass()

class GoalHourCalculator
{

}
fun CalculateHours(minID: Int, maxID: Int): Pair<String, String>
{
    var maxgoal = data.goals[GetGoalIndex(maxID)]
    var mingoal = data.goals[GetGoalIndex(minID)]
    var calcHour: String
    var goalText: String

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
        }
        else
        {
            calcHour = maxHour
            goalText = maxText
        }
    }
    else
    {
        calcHour = minHour
        goalText = minText
    }
    return Pair(calcHour, goalText)
}


//method to get the index of the goal using the ID
fun GetGoalIndex(id: Int): Int
{
    var goalID: Int = -1
    for (goal in data.goals)
    {
        if (goal.goalID == id)
        {
            goalID = goal.goalID
        }
    }
    return  goalID
}

//method to get the total hours logged in the specified interval
fun GetHours(interval: String): Int
{
    var total: Int = 0
    if (interval.equals("Daily"))
    {
        for (log in data.logs)
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
        for (log in data.logs)
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
        for (log in data.logs)
        {
            if (log.startDate.month == currentDate.month && log.startDate.year == currentDate.year)
            {
                total = total + log.hours
            }
        }
    }
    return total
}


fun CheckGoal(interval: String, amount: Int): Pair<String, String>
{
    var hourToGo: String = ""
    var hourText: String = ""

    val total = GetHours(interval)
    if (total == amount)
    {
        hourToGo = "✔"
        hourText = "Goal Reached!"
    }
    else if (total > amount)
    {
        hourToGo = (total - amount).toString()
        hourText = "Overtime"
    }
    else
    {
        hourToGo = (amount - total).toString()
        hourText = "Hours to Go!"
    }
    return Pair(hourToGo, hourText)
}




