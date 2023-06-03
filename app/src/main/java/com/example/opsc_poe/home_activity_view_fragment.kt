package com.example.opsc_poe

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
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
        for (i in GlobalClass.activities)
        {
            var newActivity = CustomActivity(activity)
            //set primary text
            newActivity.binding.tvPrimaryText.text = i.name
            //get activity category
            var index = Temp_CategoryDataClass().GetIndex(i.categoryID, GlobalClass.categories)
            var category = GlobalClass.categories[index]
            //set secondary text
            newActivity.binding.tvSecondaryText.text = category.name
            //set the activity color shape color
            val catColour = ColorStateList.valueOf(Color.parseColor(category.colour))
            newActivity.binding.llBlockText.backgroundTintList = catColour

            //get activity goal data
            var (hour, text, color) = GoalHourCalculator().CalculateHours(i.mingoalID, i.maxgoalID)

            //set the color of the divider bar between the text and the activity color shape
            val barColor = ColorStateList.valueOf(Color.parseColor(color))
            newActivity.binding.vwBar.backgroundTintList = barColor

            //set the activity color block text
            newActivity.binding.tvBlockText.text = text

            //set the activity color block time
            newActivity.binding.tvBlockX.text = hour

            //add the new view
            activityLayout.addView(newActivity)
        }
        //-------------------------------------------------
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
