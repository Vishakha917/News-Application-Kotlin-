package com.kotlin.mvvmnewsapp.db

import androidx.room.TypeConverter
import com.kotlin.mvvmnewsapp.model.Source

/**
 * Created by Vishakha Sharma on 17-05-2021.
 */
class Converters {
    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}