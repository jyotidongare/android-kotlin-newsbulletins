package com.app.newsfeed.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsDetailsServiceBuilder {

    private val baseUrl = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"

    fun getNewsDetailsService(): NewsDetailsService? {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(NewsDetailsService::class.java)
    }
}