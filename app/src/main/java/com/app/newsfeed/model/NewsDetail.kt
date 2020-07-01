package com.app.newsfeed.model

import com.google.gson.annotations.SerializedName

/**
 * Model class for holding News details data
 */
data class NewsDetail(
    var title: String = "",
    var description: String = "",

    @SerializedName("imageHref")
    var imgSource: String = ""
)