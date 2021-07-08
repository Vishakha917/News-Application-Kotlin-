package com.kotlin.mvvmnewsapp.util

/**
 * Created by Vishakha Sharma on 25-05-2021.
 */
sealed class Resource<T>(val data: T? = null, val messages: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(messages: String?, data: T? = null) : Resource<T>(data, messages)
    class Loading<T> : Resource<T>()
}