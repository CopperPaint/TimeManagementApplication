package com.example.opsc_poe

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.EditText
import com.example.opsc_poe.databinding.ActivityAddLogBinding
import com.example.opsc_poe.databinding.ActivityCalendarLogsBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class CalendarLogs : AppCompatActivity() {

    private lateinit var cvCalendar: CalendarView
    private lateinit var binding: ActivityCalendarLogsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarLogsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        val calendar = Calendar.getInstance()

        setCalendarViewDate()

        val datePicker = DatePickerDialog.OnDateSetListener {view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            var dateText = updateTable(calendar)
            binding.tvDate.text = dateText
        }

        binding.btnDate.setOnClickListener {
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
            binding.tvDate2.text = dateText
        }

        binding.btnDateEnd.setOnClickListener {
            DatePickerDialog(this, datePicker2,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

    }

    //Date Format Method
    private fun updateTable(calendar: Calendar) : String {
        val dateFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(dateFormat, Locale.UK)
        var dateText = sdf.format(calendar.time)
        return dateText
    }


    private fun setCalendarViewDate() {

        cvCalendar = findViewById(R.id.cvCalendar)
        val calendar = Calendar.getInstance()
        calendar.set(2023, Calendar.JUNE, 1)
        val selectedDate = calendar.timeInMillis
        cvCalendar.setDate(selectedDate,true,true)

    }
}