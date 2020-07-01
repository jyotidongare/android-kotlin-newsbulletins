package com.app.newsfeed.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.newsfeed.R
import com.app.newsfeed.adapter.NewsAdapter
import com.app.newsfeed.datasource.DataSource
import com.app.newsfeed.datasource.NewsFeedDataSource
import com.app.newsfeed.model.NewsDetail
import com.app.newsfeed.repository.NewsRepository
import com.app.newsfeed.service.NewsDetailsServiceBuilder


/**
 * Main Activity to load the news details list
 */
class NewsMainActivity : AppCompatActivity(), NewsDetailsContract.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipetoRefreshNewslist: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var layout: LinearLayout
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var newsAdapter: NewsAdapter? = null
    private var repository: DataSource? = null
    private var presenter: NewsDetailsContract.Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialiseView();
        initialiseListener();

        setPresenter(
            NewsDetailPresenter(
                this,
                repository = NewsRepository(NewsFeedDataSource(NewsDetailsServiceBuilder.getNewsDetailsService()!!))
            )
        )
        presenter?.onViewCreated()

    }


    private fun initialiseView() {
        swipetoRefreshNewslist = findViewById(R.id.swipe_to_refresh_newslist)

        recyclerView = findViewById(R.id.news_recycleview)
        viewManager = LinearLayoutManager(this)
        recyclerView.layoutManager = viewManager

        layout = findViewById(R.id.progress_layout)

        //Adding progressBar to UI
        progressBar = ProgressBar(this)
        progressBar.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )


        layout.addView(progressBar)

    }

    private fun initialiseListener() {
        swipetoRefreshNewslist.setOnRefreshListener({
            presenter?.loadNewsDetails()
        })
    }

    override fun setPresenter(presenter: NewsDetailsContract.Presenter) {
        this.presenter = presenter
    }


    override fun setNewsList(newsList: ArrayList<NewsDetail>) {
        if (newsAdapter == null) {
            newsAdapter = NewsAdapter(this, newsList)
            recyclerView.adapter = newsAdapter
        } else {
            newsAdapter?.newsList = newsList
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    override fun setActionBarTitle(header: String?) {
        supportActionBar?.setTitle(header)
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE

    }

    override fun hideProgressBar() {
        if (progressBar.visibility == View.VISIBLE) {
            progressBar.visibility = View.INVISIBLE
        }

        swipetoRefreshNewslist.isRefreshing = false

    }

    override fun showErrorMessage() {
        TODO("not implemented") //Show any error message
    }

    override fun onDestroy() {
        super.onDestroy()
        newsAdapter = null
        repository = null
        presenter = null
    }
}
