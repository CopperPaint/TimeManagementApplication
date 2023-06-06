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
        }

        binding.btnEndDate.setOnClickListener {
            val calendar2 = Calendar.getInstance()
            calendar2.set(2023, Calendar.JUNE, 1)

            DatePickerDialog(this, EnddatePicker,
                calendar2.get(Calendar.YEAR),
                calendar2.get(Calendar.MONTH),
                calendar2.get(Calendar.DAY_OF_MONTH)).show()
        }

        for (i in GlobalClass.activities.indices)
        {
            //if activity belongs to user
            if (GlobalClass.activities[i].userID == GlobalClass.user.userID)
            {
                for (j in GlobalClass.logs.indices)
                {
                    if (GlobalClass.logs[j].startDate.isAfter(StartDate) && (GlobalClass.logs[j].endDate.isBefore(EndDate)))
                    {
                        //create new custom activity
                        var newLog = CustomActivity(this)
                        //set primary text
                        newLog.binding.tvPrimaryText.text = GlobalClass.activities[i].name

                        //set secondary text
                        newLog.binding.tvSecondaryText.text = GlobalClass.logs[j].startDate.toString()

                        //change the text sizes
                        newLog.binding.tvPrimaryText.textSize = 14F
                        newLog.binding.tvSecondaryText.textSize = 20F

                        var catIndex = Temp_CategoryDataClass().GetIndex(
                            GlobalClass.activities[i].categoryID,
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

                        newLog.binding.tvBlockX.text = GlobalClass.DoubleToTime(GlobalClass.logs[i].hours.toString())

                        binding.llLogContainer.addView(newLog)
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
}
