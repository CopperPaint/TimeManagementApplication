package com.example.opsc_poe

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.opsc_poe.databinding.ActivityAddLogBinding
import com.example.opsc_poe.databinding.ActivityCreateCategoryBinding
import java.time.LocalDate
import java.util.Timer
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
        binding.dpHours.setIs24HourView(false)
        var isStopWatch = true


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
                inputTime = time
            }
            else
            {
                var hour = binding.dpHours.hour
                var min = binding.dpHours.minute
                val timeInMinutes = hour * 60 + min
                val timeAsDouble = timeInMinutes.toDouble()
                inputTime = timeAsDouble
            }
            var log = Temp_LogDataClass(
                logID = GlobalClass.logs.size + 1,
                activityID = activity.activityID,
                userID = GlobalClass.user.userID,
                startDate = LocalDate.of(
                    binding.dpStartDate.year,
                    binding.dpStartDate.month,
                    binding.dpStartDate.dayOfMonth),
                endDate = LocalDate.now(),
                hours = inputTime
            )
            GlobalClass.logs.add(log)
        }
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
        //binding.btnStartStop.setBackgroundResource(R.style.button_colour_start)
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