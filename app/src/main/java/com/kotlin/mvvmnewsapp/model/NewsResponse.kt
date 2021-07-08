package com.kotlin.mvvmnewsapp.model

import java.io.Serializable

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
) : Serializable