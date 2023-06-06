package com.example.opsc_poe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.opsc_poe.databinding.ActivityGlobalLogsBinding
import com.example.opsc_poe.databinding.ActivitySettingsViewBinding

class Global_Logs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Hide the action bar
        supportActionBar?.hide()

        //set status bar color
        window.statusBarColor = ContextCompat.getColor(this, R.color.Dark_Green)

        //Set view binding
        val binding = ActivityGlobalLogsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //create local fragment controller
        val fragmentControl = FragmentHandler()

        //set the activity view fragment to be the initial view
        fragmentControl.replaceFragment(global_logs_list_fragment(), R.id.fcFragmentContainer, supportFragmentManager)

        fun CycleHomeFragmentView () {

            if (binding.tvSectionTitle.text == "List") {
                binding.tvSectionTitle.text = "Category"
                fragmentControl.replaceFragment(
                    global_logs_category_fragment(),
                    R.id.fcFragmentContainer,
                    supportFragmentManager
                )
            } else {
                binding.tvSectionTitle.text = "List"
                fragmentControl.replaceFragment(
                    global_logs_list_fragment(),
                    R.id.fcFragmentContainer,
                    supportFragmentManager
                )
            }

        }

        binding.imgCycleViewLeft.setOnClickListener()
        {
            CycleHomeFragmentView()
        }

        binding.imgCycleViewRight.setOnClickListener()
        {
            CycleHomeFragmentView()
        }


    }
}