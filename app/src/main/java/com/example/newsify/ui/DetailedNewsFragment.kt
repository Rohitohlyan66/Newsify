package com.example.newsify.ui

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newsify.R
import com.example.newsify.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_detailed_news.*

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

    }
}