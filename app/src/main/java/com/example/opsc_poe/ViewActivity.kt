package com.example.opsc_poe

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.opsc_poe.databinding.ActivityViewBinding


class ViewActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Hide the action bar
        supportActionBar?.hide()

        //set status bar color
        window.statusBarColor = ContextCompat.getColor(this, R.color.Dark_Green)

        //var goalCustomActivity = CustomActivity(this)
        //CustomActivity(this).activity.binding.vwBarBacking.layoutParams = ViewGroup.LayoutParams(200, goalCustomActivity.binding.vwBarBacking.height)



        val activityIDIndex = intent.getIntExtra("activityIDIndex", 0)

        var activity = GlobalClass.activities[activityIDIndex]
        var catIndex = Temp_CategoryDataClass().GetIndex(activity.categoryID, GlobalClass.categories)
        var category = GlobalClass.categories[catIndex]
        //val catColour = ColorStateList.valueOf(Color.parseColor(category.colour))

        binding.tvActivity.text = activity.name
        binding.tvCategory.text = category.name

        //create local fragment controller
        val fragmentControl = FragmentHandler()

        //set the sign in fragment to be the initial view
        fragmentControl.replaceFragment(View_Activity_Details_Fragment(), R.id.fcFragmentContainer, supportFragmentManager)

        binding.imgSettingsButton.setOnClickListener()
        {
            var intent = Intent(this, settings_view::class.java)
            intent.putExtra("previousScreen", "Activity_View")
            intent.putExtra("currentDataID", activityIDIndex)
            //GlobalClass.InformUser("", activity.activityID.toString(), this)
            startActivity(intent)
        }

    }
}