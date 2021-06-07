package com.example.newsify.ui

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsify.R
import com.example.newsify.adapters.NewsAdapter
import com.example.newsify.model.Article
import com.example.newsify.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import kotlinx.android.synthetic.main.fragment_detailed_news.*
import kotlinx.android.synthetic.main.fragment_saved_news.*

class DetailedNewsFragment : Fragment(R.layout.fragment_detailed_news) {

    lateinit var newsViewModel: NewsViewModel

    val args: DetailedNewsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel = (activity as MainActivity).newsViewModel

        val article = args.article

        web_view.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }


        fab.setOnClickListener {
            newsViewModel.saveArticle(article)
            Snackbar.make(view, "Article Saved Successfully", Snackbar.LENGTH_LONG).show()
        }


    }

}