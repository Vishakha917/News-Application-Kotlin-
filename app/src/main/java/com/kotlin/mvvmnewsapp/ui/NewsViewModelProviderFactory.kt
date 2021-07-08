package com.kotlin.mvvmnewsapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlin.mvvmnewsapp.repository.NewsRepository

/**
 * Created by Vishakha Sharma on 25-05-2021.
 */
class NewsViewModelProviderFactory(
    private val app: Application,
    private val newsRepository: NewsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewmodel(app, newsRepository) as T
    }
}