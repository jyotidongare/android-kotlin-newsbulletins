package com.app.newsfeed.base

/**
 * Base presenter for common presenter functionality
 */
interface BasePresenter {

    fun detachView()

    fun onDestroy()
}