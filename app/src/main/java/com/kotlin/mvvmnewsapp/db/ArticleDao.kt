package com.kotlin.mvvmnewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kotlin.mvvmnewsapp.model.Article

/**
 * Created by Vishakha Sharma on 17-05-2021.
 */
@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticleNo(article: Article)
}