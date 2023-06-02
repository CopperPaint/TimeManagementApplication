package com.example.opsc_poe

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

data class Temp_LogDataClass(
    var logID: Int = 0,
    var activityID: Int = 0,
    var userID: Int = 0,
    var startDate: LocalDate,
    var endDate: LocalDate,
    var hours: Int
)
