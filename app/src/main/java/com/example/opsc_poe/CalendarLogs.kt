package com.example.opsc_poe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import java.util.*

class CalendarLogs : AppCompatActivity() {

    private lateinit var cvCalendar: CalendarView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_logs)

        setCalendarViewDate()

    }

    private fun setCalendarViewDate() {

        cvCalendar = findViewById(R.id.cvCalendar)
        val calendar = Calendar.getInstance()
        calendar.set(2023, Calendar.JUNE, 1)
        val selectedDate = calendar.timeInMillis
        cvCalendar.setDate(selectedDate,true,true)

    }
}