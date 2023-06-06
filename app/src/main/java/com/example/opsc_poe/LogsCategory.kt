package com.example.opsc_poe

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.opsc_poe.databinding.ActivityAddLogBinding
import com.example.opsc_poe.databinding.ActivityLogsCategoryBinding
import com.example.opsc_poe.databinding.HomeActivityViewFragmentBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.MPPointF
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import kotlin.collections.ArrayList

class LogsCategory : AppCompatActivity() {
    var StartDate: LocalDate? = null
    var EndDate: LocalDate? = null

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLogsCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        lateinit var pieChart: PieChart

        // on below line we are initializing our
        // variable with their ids.
        pieChart = findViewById(R.id.pieChart)

        //TEST DATA
        GlobalClass.user.userID = 2


        //DATE PICKER
        //---------------------------------------------------------------------------------
        val calendar = Calendar.getInstance()

        //START DATE
        val dateStartPicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            var dateText = updateLable(calendar)
            binding.tvStartDate.text = dateText
            StartDate = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            var data = GetChartData()
            pieChart.setData(data)
            pieChart.invalidate()
        }
        binding.btnStartDate.setOnClickListener {
            DatePickerDialog(this, dateStartPicker,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        //END DATE
        val dateEndPicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            var dateText = updateLable(calendar)
            binding.tvEndDate.text = dateText
            EndDate = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            var data = GetChartData()
            pieChart.setData(data)
            pieChart.invalidate()
        }

        binding.btnEndDate.setOnClickListener {
            DatePickerDialog(this, dateEndPicker,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        // on below line we are setting user percent value,
        // setting description as enabled and offset for pie chart
        pieChart.setUsePercentValues(false)
        pieChart.getDescription().setEnabled(false)
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)

        // on below line we are setting drag for our pie chart
        pieChart.setDragDecelerationFrictionCoef(0.95f)

        // on below line we are setting hole
        // and hole color for pie chart
        pieChart.setDrawHoleEnabled(true)
        pieChart.setHoleColor(Color.WHITE)

        // on below line we are setting circle color and alpha
        pieChart.setTransparentCircleColor(Color.WHITE)
        pieChart.setTransparentCircleAlpha(110)

        // on  below line we are setting hole radius
        pieChart.setHoleRadius(50f)
        pieChart.setTransparentCircleRadius(55f)

        // on below line we are setting center text
        pieChart.setDrawCenterText(true)

        // on below line we are setting
        // rotation for our pie chart
        pieChart.setRotationAngle(0f)

        // enable rotation of the pieChart by touch
        pieChart.setRotationEnabled(true)
        pieChart.setHighlightPerTapEnabled(true)

        // on below line we are setting animation for our pie chart
        pieChart.animateY(1400, Easing.EaseInOutQuad)

        // on below line we are disabling our legend for pie chart
        pieChart.legend.isEnabled = false
        pieChart.setEntryLabelColor(Color.WHITE)
        pieChart.setEntryLabelTextSize(12f)

        var data = GetChartData()
        pieChart.setData(data)

        // undo all highlights
        pieChart.highlightValues(null)

        // loading chart
        pieChart.invalidate()

        pieChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry, h: Highlight?) {
                // Handle section selection here
                // e?.data contains the data associated with the selected section
                var index = e.data.toString()
                var category = GlobalClass.categories[index.toInt()]
                binding.tvCategoryInformation.text = category.name
            }

            override fun onNothingSelected() {
                // Handle no section selected here
                binding.tvCategoryInformation.text = "No Category Selected"
            }
        })
    }

    //Date Format Method
    //-------------------------------------------------------------------------------
    private fun updateLable(calendar: Calendar) : String {
        val dateFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(dateFormat, Locale.UK)
        var dateText = sdf.format(calendar.time)
        return dateText
    }

    //Pie Chart Methods
    //----------------------------------------------------------------------------------------
    //method to generate the pie chart data
    @SuppressLint("Range")
    private fun GetChartData(): PieData
    {
        val entries: ArrayList<PieEntry> = ArrayList()
        val colors: ArrayList<Int> = ArrayList()
        val indexes: ArrayList<Int> = ArrayList()
        for (i in GlobalClass.categories.indices)
        {
            //if category belongs to user
            if (GlobalClass.categories[i].userID == GlobalClass.user.userID)
            {
                var hours = 0.0
                if (StartDate != null) //start date is set
                {
                    if (EndDate != null) //end date is set
                    {
                        hours = GetActivitesDataStartEnd(GlobalClass.categories[i].categoryID,StartDate!!, EndDate!!)
                    }
                    else //start only
                    {
                        hours = GetActivitesDataStart(GlobalClass.categories[i].categoryID,StartDate!!)
                    }
                }
                else
                {
                    if (EndDate != null) //end only
                    {
                        hours = GetActivitesDataEnd(GlobalClass.categories[i].categoryID, EndDate!!)

                    }
                    else //no date bounds
                    {
                        hours = GetActivitesDataNoDate(GlobalClass.categories[i].categoryID)
                    }
                }
                entries.add(PieEntry(hours.toFloat(), "", i))
                colors.add(Color.parseColor(GlobalClass.categories[i].colour))
            }
        }
        val dataSet = PieDataSet(entries, "Category Totals")

        // on below line we are setting colors.
        dataSet.colors = colors

        // on below line we are setting icons.
        dataSet.setDrawIcons(true)

        // on below line we are setting slice for pie
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // on below line we are setting pie data set
        val data = PieData(dataSet)
        data.setValueFormatter(object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return value.toInt().toString()
            }
        })
        data.setValueTextSize(15f)
        data.setValueTypeface(Typeface.DEFAULT_BOLD)
        data.setValueTextColor(Color.WHITE)

        return data
    }


    //Activity Data Methods
    //-------------------------------------------------------------------------------------
    //Get data for all user activities (no start or end date)
    fun GetActivitesDataNoDate(catID: Int): Double
    {
        var totalHour = 0.0
        for (i in GlobalClass.activities)
        {
            if (i.categoryID == catID)
            {
                for (j in GlobalClass.logs)
                {
                    if (j.activityID == i.activityID)
                    {
                        totalHour = totalHour + j.hours
                    }
                }
            }
        }
        return  totalHour
    }

    //Get user activity date between start and end date
    fun GetActivitesDataStartEnd(catID: Int, StartDate: LocalDate, EndDate: LocalDate): Double
    {
        var totalHour = 0.0
        for (i in GlobalClass.activities)
        {
            if (i.categoryID == catID)
            {
                for (j in GlobalClass.logs)
                {
                    if (j.activityID == i.activityID)
                    {
                        if (j.startDate.isAfter(StartDate) && j.startDate.isBefore(EndDate) )
                        {
                            totalHour = totalHour + j.hours
                        }
                    }
                }
            }
        }
        return  totalHour
    }

    //Get user activity data after start date
    fun GetActivitesDataStart(catID: Int, StartDate: LocalDate): Double
    {
        var totalHour = 0.0
        for (i in GlobalClass.activities)
        {
            if (i.categoryID == catID)
            {
                for (j in GlobalClass.logs)
                {
                    if (j.activityID == i.activityID)
                    {
                        if (j.startDate.isAfter(StartDate))
                        {
                            totalHour = totalHour + j.hours
                        }
                    }
                }
            }
        }
        return  totalHour
    }

    //Get user activity data before end date
    fun GetActivitesDataEnd(catID: Int, EndDate: LocalDate): Double
    {
        var totalHour = 0.0
        for (i in GlobalClass.activities)
        {
            if (i.categoryID == catID)
            {
                for (j in GlobalClass.logs)
                {
                    if (j.activityID == i.activityID)
                    {
                        if (j.startDate.isBefore(EndDate) )
                        {
                            totalHour = totalHour + j.hours
                        }
                    }
                }
            }
        }
        return  totalHour
    }
}