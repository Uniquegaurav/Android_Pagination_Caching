package com.example.manpaginationroomrecycler.data

import androidx.room.TypeConverter
import com.example.manpaginationroomrecycler.domain.model.Location
import com.example.manpaginationroomrecycler.domain.model.Origin
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: List<String>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromLocation(value: Location?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toLocation(value: String): Location {
        return gson.fromJson(value, Location::class.java)
    }

    @TypeConverter
    fun fromOrigin(value: Origin?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toOrigin(value: String): Origin {
        return gson.fromJson(value, Origin::class.java)
    }
}
