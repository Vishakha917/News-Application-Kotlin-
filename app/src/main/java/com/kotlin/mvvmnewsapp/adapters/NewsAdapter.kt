package com.kotlin.mvvmnewsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlin.mvvmnewsapp.databinding.ItemArticlePreviewBinding
import com.kotlin.mvvmnewsapp.model.Article

/**
 * Created by Vishakha Sharma on 17-05-2021.
 */
class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val itemBinding: ItemArticlePreviewBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemBinding =
            ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(itemBinding)
//        return ArticleViewHolder(
//            LayoutInflater.from(parent.context)
//                .inflate(R.layout.item_article_preview, parent, false)
//        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemBinding.apply {
            Glide.with(root).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source?.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            rootTag.setOnClickListener {
                onItemClickListener?.let { it(article) } //this->Receiver,  it->Argument
            }

//            val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//            val output = SimpleDateFormat("dd/MM/yyyy HH:mm a")
//
//            var d: Date? = null
//            try {
//                d = input.parse(article.publishedAt)
//            } catch (e: ParseException) {
//                e.printStackTrace()
//            }
//            val formatted = output.format(d)

//            val secondApiFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
//            } else {
//                TODO("VERSION.SDK_INT < O")
//            }
//            val timestamp = 1565209665.toLong() // timestamp in Long
//            val timestampAsDateString = DateTimeFormatter.ISO_INSTANT
//                .format(java.time.Instant.ofEpochSecond(timestamp))
//
//            Log.d("parseTesting", timestampAsDateString) // prints 2019-08-07T20:27:45Z
//
//            val date = LocalDate.parse(timestampAsDateString, secondApiFormat)

//            tvPublishedAt.text = date.dayOfWeek.toString() + date.month.toString() + date.dayOfYear
            tvPublishedAt.text = article.publishedAt
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}