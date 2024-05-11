package com.chunmaru.quizland.data.db.dao

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    companion object {

        fun fromJson(value: String): List<String> {
            val listType = object : TypeToken<List<String>>() {}.type
            return Gson().fromJson(value, listType)
        }

        fun toJson(values: List<String>): String {
            return Gson().toJson(values)
        }
    }


}