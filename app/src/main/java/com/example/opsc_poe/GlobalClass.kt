package com.example.opsc_poe

import android.app.Application

class GlobalClass : Application()
{
    var activities = listOf<Temp_ActivityDataClass>()
    var categories = listOf<Temp_CategoryDataClass>()


}