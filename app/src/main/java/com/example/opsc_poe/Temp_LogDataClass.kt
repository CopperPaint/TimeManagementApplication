package com.example.opsc_poe

import java.util.Date

data class Temp_LogDataClass(
    var logID: String = "",
    var activityID: String = "",
    var userID: String = "",
    var startDate: Date,
    var endDate: Date,
    var hours: Int
)
