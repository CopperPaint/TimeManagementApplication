package com.example.opsc_poe

import android.annotation.SuppressLint
import android.content.ContentProvider
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.provider.Settings.Global
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.opsc_poe.databinding.HomeActivityViewFragmentBinding

class home_activity_view_fragment : Fragment(R.layout.home_activity_view_fragment) {

    private var _binding: HomeActivityViewFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeActivityViewFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        //-------------------------------------------------
        //code here

        //global data
        //var data = GlobalClass

        val activityLayout = binding.llBars
        for (i in GlobalClass.activities.indices)
        {
            //if the activity belongs to the signed in user
            if (GlobalClass.activities[i].userID == GlobalClass.user.userID) {

                //create new custom activity
                var newActivity = CustomActivity(activity)
                //set primary text
                newActivity.binding.tvPrimaryText.text = GlobalClass.activities[i].name

                //get activity category
                var index = Temp_CategoryDataClass().GetIndex(GlobalClass.activities[i].categoryID, GlobalClass.categories)
                var category = GlobalClass.categories[index]

                //set secondary text
                newActivity.binding.tvSecondaryText.text = category.name

                //set the activity color shape color
                val catColour = ColorStateList.valueOf(Color.parseColor(category.colour))
                newActivity.binding.llBlockText.backgroundTintList = catColour
                //newActivity.binding.llBlockText.backgroundTintList =  ColorStateList.valueOf(Color.parseColor("#5c37d7"))

                var hour = ""
                var text = ""

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

                GlobalClass.InformUser("", "Activity Name: $GlobalClass.activities[i].name \n Category Name: $category.name \n Time Text: $text \n Hour: $hour \n Min Goal: ${GlobalClass.activities[i].mingoalID.toString()} \n Max Goal: ${GlobalClass.activities[i].maxgoalID.toString()}", requireContext())
            }
        }
        //-------------------------------------------------
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
