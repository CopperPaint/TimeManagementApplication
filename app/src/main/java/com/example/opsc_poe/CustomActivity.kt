package com.example.opsc_poe

import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.fragment.app.FragmentActivity
import com.example.opsc_poe.databinding.CustomactivityBinding

class CustomActivity(
    context: FragmentActivity? //FragmentActivity? was Context
) : RelativeLayout(context)
{
    var binding: CustomactivityBinding
    
    init
    {
        //val binding = ActivityMainBinding.inflate()
       binding = CustomactivityBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
        //inflate(context, R.layout.customactivity, this)
    }
}