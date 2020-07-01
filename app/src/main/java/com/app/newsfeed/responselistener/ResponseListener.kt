package com.app.newsfeed.responselistener

interface ResponseListener<T> {

    fun onResponse(t: T);

}