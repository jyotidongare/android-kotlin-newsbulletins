package com.app.newsfeed.activity

import com.app.newsfeed.datasource.DataSource
import com.app.newsfeed.responselistener.ResponseErrorListener
import com.app.newsfeed.responselistener.ResponseListener
import com.app.newsfeed.model.NewsFeedDetails

/**
 * NewsDetail Presenter bind with view,
 * presenter request repository to get the news details list and updateview
 */
class NewsDetailPresenter(
    view: NewsDetailsContract.View,
    repository: DataSource
) : NewsDetailsContract.Presenter {


    private var view: NewsDetailsContract.View? = view
    private var repository: DataSource? = repository

    override fun onViewCreated() {

        view?.showProgressBar()
        loadNewsDetails()

    }

    override fun loadNewsDetails() {

        repository?.getNewsDetailsList(responseListener = object :
            ResponseListener<NewsFeedDetails> {
            override fun onResponse(newsFeedDetails: NewsFeedDetails) {

                loadNewsDetails(newsFeedDetails)

            }
        }, errorListener = object : ResponseErrorListener {
            override fun sendErrorMessage(errorMessage: String) {

                view?.hideProgressBar()
                view?.showErrorMessage()
            }
        })

    }

    private fun loadNewsDetails(newsFeedDetails: NewsFeedDetails) {
        view?.hideProgressBar()
        view?.setNewsList(newsFeedDetails.newsList)
        view?.setActionBarTitle(newsFeedDetails.header)
    }

    override fun detachView() {
        view?.onDestroy()
    }

    override fun onDestroy() {
        this.view = null
        repository = null
    }

}