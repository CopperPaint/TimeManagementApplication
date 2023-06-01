package com.example.opsc_poe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.example.opsc_poe.databinding.HomeActivityViewFragmentBinding

class CategoryName : AppCompatActivity() {
    private var _binding: HomeActivityViewFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_name)

        //global data
        var globaldata = GlobalClass()

        //passed category
        var category = Temp_CategoryDataClass()




        val CategoryName = findViewById<TextView>(R.id.tvCategoryName)
        CategoryName.text = category.name


        val CategoryDescription = findViewById<TextView>(R.id.txtCategoryDescription)
        CategoryDescription.text = category.description


        val activityLayout = findViewById<LinearLayout>(R.id.llactivitycontainer)

        for (activity in globaldata.activities)
        {
            if (activity.categoryID == category.categoryID)
            {
                //FIX THIS
                val activityLayout = binding.llBars
                var newActivity = CustomActivity(this)
                newActivity.binding.tvPrimaryText.text = activity.name
                newActivity.binding.tvSecondaryText.text = ""


                //add to view
                activityLayout.addView(newActivity)

            }





        }










        val addactivityButton = findViewById<Button>(R.id.btnAddActvity)
        addactivityButton?.setOnClickListener()
        {
            //go to add actvity page
        }

        val editCategory = findViewById<Button>(R.id.btnEditCategory)
        addactivityButton?.setOnClickListener()
        {
            //go to add actvity page
        }



    }
}
