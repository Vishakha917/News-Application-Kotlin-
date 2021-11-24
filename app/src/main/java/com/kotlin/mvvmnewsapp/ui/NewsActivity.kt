package com.kotlin.mvvmnewsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.ViewBinding
import com.kotlin.mvvmnewsapp.R
import com.kotlin.mvvmnewsapp.databinding.ActivityNewsBinding
import com.kotlin.mvvmnewsapp.db.ArticleDatabase
import com.kotlin.mvvmnewsapp.repository.NewsRepository

class NewsActivity : AppCompatActivity() { //this@NewsActivity
    private val binding by viewBinding(ActivityNewsBinding::inflate)
    private lateinit var navController: NavController
    private lateinit var viewmodel: NewsViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val repository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, repository)
        viewmodel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewmodel::class.java)

        binding.apply {
            setContentView(root)
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
            navController = navHostFragment.navController
            bottomNavigationView.setupWithNavController(navController)

            val appBarConfiguration = AppBarConfiguration(
                topLevelDestinationIds = setOf(
                    R.id.breakingNewsFragment2,
                    R.id.savedNewsFragment2,
                    R.id.searchNewsFragment2
                )
            )
            setupWithNavController(binding.toolbar, navController, appBarConfiguration)

//            navController.addOnDestinationChangedListener(
//                NavController.OnDestinationChangedListener()
//            )
        }

    }

    private inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
        crossinline bindingInflater: (LayoutInflater) -> T
    ) =
        lazy(LazyThreadSafetyMode.NONE) {
            bindingInflater.invoke(layoutInflater)
        }
}