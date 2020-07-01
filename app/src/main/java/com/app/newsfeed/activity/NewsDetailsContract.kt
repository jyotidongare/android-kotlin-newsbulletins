package com.app.newsfeed.activity

import com.app.newsfeed.base.BasePresenter
import com.app.newsfeed.base.BaseView
import com.app.newsfeed.model.NewsDetail

/**
 * Contract to hold the abstraction of News Details Presenter and View
 */
interface NewsDetailsContract {

    interface Presenter : BasePresenter {

        fun onViewCreated()
        fun loadNewsDetails()
    }

    interface View : BaseView<Presenter> {

        fun setNewsList(newsList: ArrayList<NewsDetail>)
        fun setActionBarTitle(header: String?)
        fun showErrorMessage()
        fun showProgressBar()
        fun hideProgressBar()
        fun onDestroy()
    }

}