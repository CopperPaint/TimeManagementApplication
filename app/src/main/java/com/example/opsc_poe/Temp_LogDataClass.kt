package com.example.opsc_poe

import java.util.Date

data class Temp_LogDataClass(
    var logID: Int = 0,
    var activityID: Int = 0,
    var userID: Int = 0,
    var startDate: Date,
    var endDate: Date,
    var hours: Int
)
