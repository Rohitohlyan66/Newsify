package com.example.newsify.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsify.R
import com.example.newsify.model.Article
import kotlinx.android.synthetic.main.article_preview.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffUtilCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtilCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.article_preview, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val article = differ.currentList[position]

        holder.itemView.apply {
            tv_newsSource.text = article.source.name
            tv_newsDescription.text = article.description
            tv_newsPublishedAt.text = "Published At - ${article.publishedAt}"
            tv_newsTitle.text = article.title
            Glide.with(this).load(article.urlToImage).into(iv_newsImage)

            setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }


    }

    override fun getItemCount(): Int {
        return differ.currentList.count()
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

}