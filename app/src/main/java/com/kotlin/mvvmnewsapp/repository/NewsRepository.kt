package com.kotlin.mvvmnewsapp.repository

import com.kotlin.mvvmnewsapp.api.RetrofitClient
import com.kotlin.mvvmnewsapp.db.ArticleDatabase
import com.kotlin.mvvmnewsapp.model.Article

/**
 * Created by Vishakha Sharma on 25-05-2021.
 */
class NewsRepository(val db: ArticleDatabase) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitClient.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitClient.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}