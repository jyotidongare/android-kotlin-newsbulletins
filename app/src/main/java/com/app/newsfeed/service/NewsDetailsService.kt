package com.app.newsfeed.service

import com.app.newsfeed.model.NewsFeedDetails
import retrofit2.Call
import retrofit2.http.GET

open interface NewsDetailsService {

    @GET("facts.json")
    fun getNewsDetails(): Call<NewsFeedDetails>
}