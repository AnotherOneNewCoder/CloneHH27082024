package ru.zhogin.app.search.common

fun convertData(data : String) : String {
    val list = data.split('-')
    if (list.size < 2) {
        return "wrong format"
    } else {
        val day = if (list[2].startsWith('0')) list[2].drop(1) else list[2]
        val month: String = when(list[1]) {
            "01" -> "января"
            "02" -> "февраля"
            "03" -> "марта"
            "04" -> "апреля"
            "05" -> "мая"
            "06" -> "июня"
            "07" -> "июля"
            "08" -> "августа"
            "09" -> "сентября"
            "10" -> "октября"
            "11" -> "ноября"
            "12" -> "декабря"
            else -> return "wrong format"
        }
        return "$day $month"
    }
}