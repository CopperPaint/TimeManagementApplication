package com.example.opsc_poe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

        //sign in button

       // binding.tvSignInButton.setOnClickListener {



           // }

        for (i in 1..10) {
            //create new views
           // var newBar = TextView(activity)
           // newBar.top = 10
           // newBar.text = i.toString()


            val activityLayout = binding.llBars
            var newActivity = CustomActivity(activity)


            val tvPrimaryText = newActivity.findViewById<TextView>(R.id.tvPrimaryText)
            tvPrimaryText.text = "Bar Number: " + i.toString()


            /*
            newActivity.tvP
                //"Bar Number: " + i.toString()

             */

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
