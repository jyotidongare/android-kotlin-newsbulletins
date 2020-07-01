package com.app.newsfeed.model

import com.google.gson.annotations.SerializedName

/**
 * News Feed Details Model to map gson data
 */
data class NewsFeedDetails(
    @SerializedName("title")
    var header: String? = null,

    @SerializedName("rows")
    var newsList: ArrayList<NewsDetail> = ArrayList<NewsDetail>()
)