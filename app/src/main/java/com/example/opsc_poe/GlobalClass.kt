package com.example.opsc_poe

import android.app.Application

class GlobalClass : Application()
{
    var activities = arrayListOf<Temp_ActivityDataClass>()
    var categories = arrayListOf<Temp_CategoryDataClass>()
    var logs = arrayListOf<Temp_LogDataClass>()
    var user = Temp_UserDataClass()
}