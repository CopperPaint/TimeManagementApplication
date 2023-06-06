package com.example.opsc_poe

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
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
        setContentView(R.layout.activity_logs_list)

        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            var dateText = updateTable(calendar)
            binding.tvStartDate.text = dateText
            StartDate = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        }

        binding.btnStartDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(this, datePicker,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show()

        }

        val datePicker2 = DatePickerDialog.OnDateSetListener {view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            var dateText = updateTable(calendar)
            binding.tvEndDate.text = dateText
            EndDate = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        }

        binding.btnEndDate.setOnClickListener {
            val calendar2 = Calendar.getInstance()
            calendar2.set(2023, Calendar.JUNE, 1)

            DatePickerDialog(this, datePicker2,
                calendar2.get(Calendar.YEAR),
                calendar2.get(Calendar.MONTH),
                calendar2.get(Calendar.DAY_OF_MONTH)).show()
        }

        val activityLayout = binding.llLogsCategory

        for (l in GlobalClass.activities.indices)
        {
            for (i in GlobalClass.logs.indices) {
                //if the activity belongs to the signed in user
                if (GlobalClass.logs[i].activityID == GlobalClass.activities[l].activityID &&
                    (GlobalClass.logs[i].startDate.isAfter(StartDate))
                    && (GlobalClass.logs[i].endDate.isBefore(EndDate)))
                {
                    //create new custom activity
                    var newLog = CustomActivity(this)
                    //set primary text
                    newLog.binding.tvPrimaryText.text = GlobalClass.activities[l].name

                    //get activity category
                    // var index = Temp_CategoryDataClass().GetIndex(GlobalClass.activities[i].categoryID, GlobalClass.categories)
                    // var category = GlobalClass.categories[index]

                    //set secondary text
                    newLog.binding.tvSecondaryText.text =
                        GlobalClass.logs[i].startDate.toString()//category.name

                    //change the text sizes
                    newLog.binding.tvPrimaryText.textSize = 14F
                    newLog.binding.tvSecondaryText.textSize = 20F

                    var catIndex = Temp_CategoryDataClass().GetIndex(
                        GlobalClass.activities[l].categoryID,
                        GlobalClass.categories
                    )
                    var category = GlobalClass.categories[catIndex]

                    //set the activity color shape color
                    val catColour = ColorStateList.valueOf(Color.parseColor(category.colour))

                    //ColorStateList.valueOf(Color.parseColor(category.colour))
                    newLog.binding.llBlockText.backgroundTintList = catColour


                    val barColor = ContextCompat.getColorStateList(
                        this,
                        R.color.Default_Charcoal_Grey
                    )

                    newLog.binding.vwBar.backgroundTintList = barColor
                    newLog.binding.tvBlockText.text = "Hours Logged"
                    newLog.binding.tvBlockX.text =
                        GlobalClass.DoubleToTime(GlobalClass.logs[i].hours.toString())
                    //newActivity.binding.llBlockText.backgroundTintList =  ColorStateList.valueOf(Color.parseColor("#5c37d7"))

                    //add the new view
                    activityLayout.addView(newLog)
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
}