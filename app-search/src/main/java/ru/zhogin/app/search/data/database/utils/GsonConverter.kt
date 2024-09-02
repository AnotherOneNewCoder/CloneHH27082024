package ru.zhogin.app.search.data.database.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object GsonConverter {
    private val gson = Gson()

    @TypeConverter
    fun List<String>.toJson() : String {
        return gson.toJson(this)
    }
    @TypeConverter
    fun String.toType() : List<String> {
        return if (this.isEmpty()) {
            listOf()
        } else {
            val type = object : TypeToken<List<String>>() {}.type
            gson.fromJson(this, type)
        }
    }
}