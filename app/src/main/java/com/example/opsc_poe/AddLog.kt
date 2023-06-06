package com.example.opsc_poe

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.opsc_poe.databinding.ActivityAddLogBinding
import com.example.opsc_poe.databinding.ActivityCreateCategoryBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import kotlin.math.round
import kotlin.math.roundToInt

class AddLog : AppCompatActivity() {
    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0
    private lateinit var binding: ActivityAddLogBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddLogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //passed activity
        var activity = GlobalClass.activities[3]

        //show activity name
        binding.tvActivityName.text = activity.name

        //set hour picker
        binding.dpHours.setIs24HourView(true)
        var isStopWatch = true


        //DATE PICKER
        //---------------------------------------------------------------------------------
        val calendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            var dateText = updateLable(calendar)
            binding.tvDate.text = dateText
        }

        binding.btnDate.setOnClickListener {
            DatePickerDialog(this, datePicker,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show()
        }










        //SPINNER
        //------------------------------------------------------------------------------------
        //set spinner items
        val items = arrayOf("Stopwatch", "Input Hours")
        val spinner = findViewById<Spinner>(R.id.spWatchOption)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, items)
            spinner.adapter = adapter
        }

        //spinner is changed
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                when (selectedItem) {
                    "Stopwatch" -> {
                        binding.llStopWatch.visibility = View.VISIBLE
                        binding.llInputHours.visibility = View.GONE
                        isStopWatch = true
                    }
                    "Input Hours" -> {
                        // Code to execute when "Input Hours" is selected
                        binding.llStopWatch.visibility = View.GONE
                        binding.llInputHours.visibility = View.VISIBLE
                        isStopWatch = false
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to execute when nothing is selected
            }
        }


        //TIMER
        //-------------------------------------------------------------------------------------
        binding.btnStartStop.setOnClickListener() {startStopTimer()}
        binding.btnReset.setOnClickListener() {resetTimer()}

        serviceIntent = Intent(applicationContext, TimerService::class.java)
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))



        //SAVE
        //--------------------------------------------------------------------------------------
        //save goal
        binding.btnAdd.setOnClickListener()
        {
            var inputTime = 0.0
            if (isStopWatch)
            {
                inputTime = round((time/60.0) * 100) / 100
            }
            else
            {
                val hour = binding.dpHours.hour
                val min = binding.dpHours.minute
                val timeInHours = hour + (min / 60.0)
                inputTime = round(timeInHours * 100) / 100
            }
            var log = Temp_LogDataClass(
                logID = GlobalClass.logs.size + 1,
                activityID = activity.activityID,
                userID = GlobalClass.user.userID,
                startDate = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                endDate = LocalDate.now(),
                hours = inputTime
            )
            GlobalClass.logs.add(log)
        }
    }

    //Date Format Method
    private fun updateLable(calendar: Calendar) : String {
        val dateFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(dateFormat, Locale.UK)
        var dateText = sdf.format(calendar.time)
        return dateText
    }


    //TIMER METHODS
    //------------------------------------------------------------------------------------
    private fun resetTimer()
    {
        stopTimer()
        time = 0.0
        binding.tvTime.text = getTimeStringFromDouble(time)
    }

    private fun startStopTimer()
    {
        if(timerStarted)
            stopTimer()
        else
            startTimer()
    }


    private fun startTimer()
    {
        serviceIntent.putExtra(TimerService.TIME_EXTRA, time)
        startService(serviceIntent)
        binding.btnStartStop.text = "Stop"
        binding.btnStartStop.setCompoundDrawablesWithIntrinsicBounds(R.drawable.pause, 0, 0, 0)
        timerStarted = true
    }

    private fun stopTimer() {
        stopService(serviceIntent)
        binding.btnStartStop.text = "Start"
        binding.btnStartStop.setCompoundDrawablesWithIntrinsicBounds(R.drawable.play_arrow, 0, 0, 0)
        timerStarted = false
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver()
    {
        override fun onReceive(context: Context, intent: Intent)
        {
            time = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            binding.tvTime.text = getTimeStringFromDouble(time)
        }
    }

    private fun getTimeStringFromDouble(time: Double): CharSequence?
    {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hour: Int, min: Int, sec: Int): String = String.format("%02d:%02d:%02d", hour, min, sec)

}