package com.example.opsc_poe

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.opsc_poe.databinding.HomeCategoryViewFragmentBinding

class home_category_view_fragment : Fragment(R.layout.home_category_view_fragment) {

    private var _binding: HomeCategoryViewFragmentBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeCategoryViewFragmentBinding.inflate(inflater, container, false)
        val view = binding.root


        //-------------------------------------------------
        //code here
        val activityLayout = binding.llBars
        for (i in GlobalClass.categories.indices)
        {
            //if the category belongs to the signed in user
            if (GlobalClass.categories[i].userID == GlobalClass.user.userID)
            {

                //create new custom activity
                var newCategory = CustomActivity(activity)
                //get category data
                val catColour = ColorStateList.valueOf(Color.parseColor(GlobalClass.categories[i].colour))
                var (actTotal, hourTotal) = GetActivitesData(GlobalClass.categories[i].categoryID)
                //set primary text
                newCategory.binding.tvPrimaryText.text = GlobalClass.categories[i].name
                //set secondary text
                newCategory.binding.tvSecondaryText.text = "No of activties: " + actTotal
                //set the color of the divider bar between the text and the activity color shape
                newCategory.binding.vwBar.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.Default_Charcoal_Grey)
                //set the activity color shape color
                newCategory.binding.llBlockText.backgroundTintList = catColour
                //set the activity color block text
                newCategory.binding.tvBlockText.text = "Total Hours:"
                //set the activity color block time
                newCategory.binding.tvBlockX.text = hourTotal.toString()
                //add the new view
                activityLayout.addView(newCategory)
            }
        }
        //-------------------------------------------------


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun GetActivitesData(catID: Int): Pair<Int, Int>
    {
        var totalAct = 0
        var totalHour = 0
        for (i in GlobalClass.activities)
        {
            if (i.categoryID == catID)
            {
                totalAct++
                for (j in GlobalClass.logs)
                {
                    if (j.activityID == i.activityID)
                    {
                        totalHour = totalHour + j.hours
                    }
                }
            }
        }
        return  Pair(totalAct, totalHour)
    }

}
