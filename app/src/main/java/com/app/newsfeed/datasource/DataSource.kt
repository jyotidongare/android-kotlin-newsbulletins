package com.app.newsfeed.datasource

import com.app.newsfeed.model.NewsFeedDetails
import com.app.newsfeed.responselistener.ResponseErrorListener
import com.app.newsfeed.responselistener.ResponseListener

/**
 * DataSource abstraction for getting New details
 */
interface DataSource {

    fun getNewsDetailsList(
        responseListener: ResponseListener<NewsFeedDetails>,
        errorListener: ResponseErrorListener
    )

}