package com.app.newsfeed.datasource

import com.app.newsfeed.model.NewsFeedDetails
import com.app.newsfeed.responselistener.ResponseErrorListener
import com.app.newsfeed.responselistener.ResponseListener
import com.app.newsfeed.service.NewsDetailsService
import retrofit2.Call
import retrofit2.Callback

/**
 * News details Data Source implmentation
 * Using Retrofit library to request server for fetching news details list ,
 * parse the data and return to repository
 */
class NewsFeedDataSource(val newsDetailsService: NewsDetailsService) : DataSource {

    override fun getNewsDetailsList(
        responseListener: ResponseListener<NewsFeedDetails>,
        errorListener: ResponseErrorListener
    ) {

        newsDetailsService.getNewsDetails()
            .enqueue(object : Callback<NewsFeedDetails> {
                override fun onFailure(call: Call<NewsFeedDetails>, t: Throwable) {
                    errorListener.sendErrorMessage(t.message!!)
                }

                override fun onResponse(
                    call: Call<NewsFeedDetails>,
                    response: retrofit2.Response<NewsFeedDetails>
                ) {
                    if (response.isSuccessful) {
                        responseListener.onResponse(response.body()!!)
                    }
                }
            })
    }
}