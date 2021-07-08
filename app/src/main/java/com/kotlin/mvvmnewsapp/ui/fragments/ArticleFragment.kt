package com.kotlin.mvvmnewsapp.ui.fragments

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.kotlin.mvvmnewsapp.R
import com.kotlin.mvvmnewsapp.databinding.FragmentArticleBinding
import com.kotlin.mvvmnewsapp.ui.NewsActivity
import com.kotlin.mvvmnewsapp.ui.NewsViewmodel

class ArticleFragment : Fragment(R.layout.fragment_article) {
    lateinit var viewModel : NewsViewmodel
    lateinit var binding : FragmentArticleBinding
    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)
        viewModel = ViewModelProvider(activity as NewsActivity).get(NewsViewmodel::class.java)

        val article = args.article
        Log.e(javaClass.name, "article : $article")

        binding.fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Article saved successfully.", Snackbar.LENGTH_SHORT).show()
        }

        binding.webView.apply {
            article.url?.let { loadUrl(it) }
            webChromeClient = WebChromeClient()

            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                }

                override fun onPageFinished(view: WebView, url: String) {
                    super.onPageFinished(view, url)
                }

                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    request: WebResourceRequest
                ): Boolean {
//                    val url=request?.url.toString()
//                    if(Uri.parse(url).host=="www.example.com")
//                    {
//                        return false
//                    }
//                    val intent= Intent(Intent.ACTION_VIEW,Uri.parse(url));
//                    startActivity(intent)
                    return true
                }

                override fun onReceivedSslError(
                    view: WebView,
                    handler: SslErrorHandler,
                    error: SslError
                ) {
                    super.onReceivedSslError(view, handler, error)
                }
            }

        }

    }

}