package com.example.opsc_poe

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.opsc_poe.databinding.ActivityLogsCategoryBinding
import com.example.opsc_poe.databinding.ActivityLogsListBinding
import com.example.opsc_poe.databinding.ActivityViewLogsFragmentBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class LogsList : AppCompatActivity() {

    private lateinit var binding: ActivityLogsListBinding
    var StartDate: LocalDate? = null
    var EndDate: LocalDate? = null

    //private val binding get() = binding!!
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //generate full list
        GenerateLogList()

        //Date Pickers
        //-----------------------------------------------------------------------------------------
        val calendar = Calendar.getInstance()
        val StartdatePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            var dateText = updateTable(calendar)
            binding.tvStartDate.text = dateText
            StartDate = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            GenerateLogList()
        }

        binding.btnStartDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(this, StartdatePicker,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show()

        }

        val EnddatePicker = DatePickerDialog.OnDateSetListener {view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            var dateText = updateTable(calendar)
            binding.tvEndDate.text = dateText
            EndDate = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            GenerateLogList()
        }

        binding.btnEndDate.setOnClickListener {
            val calendar2 = Calendar.getInstance()
            calendar2.set(2023, Calendar.JUNE, 1)

            DatePickerDialog(this, EnddatePicker,
                calendar2.get(Calendar.YEAR),
                calendar2.get(Calendar.MONTH),
                calendar2.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun GenerateLogList()
    {
        binding.llLogContainer.removeAllViews()
        var loglist = GlobalClass.logs
        loglist.sortBy { it.startDate }

        for (i in loglist.indices)
        {
            if (loglist[i].userID == GlobalClass.user.userID)
            {
                var activityIndex = 0
                //get activity index
                for (j in GlobalClass.activities.indices)
                {
                    if (loglist[i].activityID == GlobalClass.activities[j].activityID)
                    {
                        activityIndex = j
                    }
                }
                var activity = GlobalClass.activities[activityIndex]

                if (StartDate != null)
                {
                    if (EndDate != null) //both dates
                    {
                        if (loglist[i].startDate.isAfter(StartDate) && (loglist[i].endDate.isBefore(EndDate)))
                        {
                            AddLogView(loglist[i], activity)
                        }
                    }
                    else //start only
                    {
                        if (loglist[i].startDate.isAfter(StartDate))
                        {
                            AddLogView(loglist[i], activity)
                        }
                    }
                }
                else
                {
                    if (EndDate != null) //End Date Only
                    {
                        if ((loglist[i].endDate.isBefore(EndDate)))
                        {
                            AddLogView(loglist[i], activity)
                        }
                    }
                    else //start only
                    {
                        AddLogView(loglist[i], activity)
                    }
                }
            }
        }
    }

    private fun updateTable(calendar: Calendar) : String {
        val dateFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(dateFormat, Locale.UK)
        var dateText = sdf.format(calendar.time)
        return dateText
    }

    @SuppressLint("Range")
    private fun AddLogView(log: Temp_LogDataClass, activity: Temp_ActivityDataClass)
    {
        //create new custom activity
        var newLog = CustomActivity(this)
        //set primary text
        newLog.binding.tvPrimaryText.text = activity.name

        //set secondary text
        newLog.binding.tvSecondaryText.text = log.startDate.toString()

        //change the text sizes
        newLog.binding.tvPrimaryText.textSize = 14F
        newLog.binding.tvSecondaryText.textSize = 20F

        var catIndex = Temp_CategoryDataClass().GetIndex(
            activity.categoryID,
            GlobalClass.categories
        )
        var category = GlobalClass.categories[catIndex]
        //set the activity color shape color
        val catColour = ColorStateList.valueOf(Color.parseColor(category.colour))
        //ColorStateList.valueOf(Color.parseColor(category.colour))
        newLog.binding.llBlockText.backgroundTintList = catColour

        //set bar color
        val barColor = ContextCompat.getColorStateList(
            this,
            R.color.Default_Charcoal_Grey
        )
        newLog.binding.vwBar.backgroundTintList = barColor

        newLog.binding.tvBlockText.text = "Hours Logged"

        newLog.binding.tvBlockX.text = GlobalClass.DoubleToTime(log.hours.toString())

        binding.llLogContainer.addView(newLog)
    }
}
