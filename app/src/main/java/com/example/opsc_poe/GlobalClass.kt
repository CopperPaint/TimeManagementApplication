package com.example.opsc_poe

import android.app.AlertDialog
import android.app.Application
import android.content.Context



class GlobalClass : Application()
{

    companion object {


        var activities = arrayListOf<Temp_ActivityDataClass>()
        var categories = arrayListOf<Temp_CategoryDataClass>()
        var goals = arrayListOf<Temp_GoalDataClass>()
        var logs = arrayListOf<Temp_LogDataClass>()
        var user = Temp_UserDataClass()


        fun InformUser(messageTitle: String, messageText: String, context: Context) {
            val alert = AlertDialog.Builder(context)
            alert.setTitle(messageTitle)
            alert.setMessage(messageText)
            alert.setPositiveButton("OK", null)
            alert.show()
        }

        //---------------------------------------------------------------------------------------------------------------------------------------------
        //temporary lists of registered users and their data
        //---------------------------------------------------------------------------------------------------------------------------------------------
        var listUserUserID = arrayListOf(1, 2, 3, 4, 5)
        var listUserEmail = arrayListOf(
            "jake@turtletime.com",
            "joe@turtletime.com",
            "benji@turtletime.com",
            "marker@turtletime.com",
            "jrt"
        )
        var listUserUsername = arrayListOf("jake", "joe", "benji", "marker", "jrtu")
        var listUserPasswordHash = arrayListOf(
            " ",
            " ",
            " ",
            " ",
            "1a13941cffe6e1768dca77b6d37eb317f06993944da80ff5193f3f7c691a95df"
        )
        var listUserPasswordSalt = arrayListOf(" ", " ", " ", " ", "221509276dd043002f0b8771957f556b")

        //password for jrt account is "jrtp"
        //---------------------------------------------------------------------------------------------------------------------------------------------



    }

    override fun onCreate() {
        super.onCreate()
    }



}