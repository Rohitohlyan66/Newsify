package com.example.newsify.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.newsify.R
import com.example.newsify.adapters.NewsAdapter
import com.example.newsify.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import kotlinx.android.synthetic.main.fragment_saved_news.*
import java.util.Observer

class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    lateinit var newsViewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    val TAG = "SAVED NEWS"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel = (activity as MainActivity).newsViewModel
        setUpRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_detailedNewsFragment,
                bundle
            )
        }

        saved_fab_goToTop.setOnClickListener {
            rv_saved_news.smoothScrollToPosition(0)
        }

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rv_saved_news)

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val position = viewHolder.adapterPosition
                val deletedArticle = newsAdapter.differ.currentList[position]
                newsViewModel.deleteArticle(deletedArticle)

                Snackbar.make(view, "Item Deleted Successfully", Snackbar.LENGTH_LONG).apply {
                    setAction("UNDO") {
                        newsViewModel.saveArticle(deletedArticle)

                    }
                    show()
                }

            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(rv_saved_news)
        }

        newsViewModel.getSavedNews()
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer { articles ->
                newsAdapter.differ.submitList(articles)
            })

    }

    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter()
        rv_saved_news.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}