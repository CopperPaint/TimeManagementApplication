package com.example.opsc_poe

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.EditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class CalendarLogs : AppCompatActivity() {

    private lateinit var cvCalendar: CalendarView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_logs)

        setCalendarViewDate()


        val txtDate: EditText = findViewById(R.id.txtDate)

        setdate(txtDate.text.toString())

    }

    private fun setdate(txtDateString : String)
    {

        val inputFormat = SimpleDateFormat("yyyy-mm-dd",Locale.getDefault())
        val outputFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())

        val date: Date? = inputFormat.parse(txtDateString)

        if (date != null) {

            val cvCalendar: CalendarView = findViewById(R.id.cvCalendar)
            val calendar = Calendar.getInstance()
           // calendar.time = date

           // val formattedDate = outputFormat.format(date)
            val selectedDate = calendar.timeInMillis


            cvCalendar.date = selectedDate
            cvCalendar.setDate(selectedDate,true,true)

        }

    }

    private fun setCalendarViewDate() {

        cvCalendar = findViewById(R.id.cvCalendar)
        val calendar = Calendar.getInstance()
        calendar.set(2023, Calendar.JUNE, 1)
        val selectedDate = calendar.timeInMillis
        cvCalendar.setDate(selectedDate,true,true)

    }
}