package ru.zhogin.app.search.common

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale


fun scheduleFromList(schedules: List<String>) : String {
    var result = ""
    for (schedule in schedules) {
        result += "$schedule, "
    }
    return result.dropLast(2).capitalize(Locale.current)
}