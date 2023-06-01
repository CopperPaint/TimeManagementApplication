package com.example.opsc_poe

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeActivityViewFragmentBinding.inflate(inflater, container, false)
        val view = binding.root


        //-------------------------------------------------
        //code here

        for (i in 1..10) {
            //create new views
            val activityLayout = binding.llBars
            var newActivity = CustomActivity(activity)
            newActivity.binding.tvPrimaryText.text = "Bar Number: " + i.toString()
            newActivity.binding.vBlock.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.Light_Green)
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
