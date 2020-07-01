package com.app.newsfeed.repository

import com.app.newsfeed.datasource.DataSource
import com.app.newsfeed.responselistener.ResponseErrorListener
import com.app.newsfeed.responselistener.ResponseListener
import com.app.newsfeed.model.NewsFeedDetails

/**
 * News Repository to connect with Data source for requesting news data
 */
class NewsRepository(private val dataSource: DataSource) : DataSource {

    override fun getNewsDetailsList(
        responseListener: ResponseListener<NewsFeedDetails>,
        errorListener: ResponseErrorListener
    ) {

        dataSource.getNewsDetailsList(responseListener = object :
            ResponseListener<NewsFeedDetails> {
            override fun onResponse(newsFeedDetails: NewsFeedDetails) {
                responseListener.onResponse(newsFeedDetails)
            }
        }, errorListener = object : ResponseErrorListener {
            override fun sendErrorMessage(errorMessage: String) {
                errorListener.sendErrorMessage(errorMessage)
            }
        })
    }


}