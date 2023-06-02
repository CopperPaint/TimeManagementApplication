package com.example.opsc_poe

import android.content.Intent
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeCategoryViewFragmentBinding.inflate(inflater, container, false)
        val view = binding.root


        //-------------------------------------------------
        //code here

        for (i in 1..10) {

            //create new views
            val activityLayout = binding.llBars
            var newActivity = CustomActivity(activity)

            //set primary text
            newActivity.binding.tvPrimaryText.text = "Category " + i.toString()

            //set secondary text (if second line is not needed then set to null
            newActivity.binding.tvSecondaryText.text = "Secondary " + i.toString()

            //set the color of the divider bar between the text and the activity color shape
            newActivity.binding.vwBar.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.Light_Green)

            //set the activity color shape color
            newActivity.binding.llBlockText.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.Dark_Green)

            //set the activity color block text
            newActivity.binding.tvBlockText.text = "Hours to go!"

            //set the activity color block time
            newActivity.binding.tvBlockX.text = i.toString()

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
