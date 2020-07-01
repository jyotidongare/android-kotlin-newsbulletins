package com.app.newsfeed.activity

import com.app.newsfeed.datasource.DataSource
import com.app.newsfeed.model.NewsDetail
import com.app.newsfeed.model.NewsFeedDetails
import com.app.newsfeed.responselistener.ResponseErrorListener
import com.app.newsfeed.responselistener.ResponseListener
import org.junit.Before
import org.junit.Test
import org.mockito.*

/**
 * News Detail Presenter Test
 */
class NewsDetailPresenterTest {

    @Mock
    private lateinit var view: NewsDetailsContract.View

    @Mock
    private lateinit var reposiotry: DataSource

    private lateinit var presenter: NewsDetailPresenter

    @Captor
    private lateinit var responseCaptor: ArgumentCaptor<ResponseListener<NewsFeedDetails>>

    @Captor
    private lateinit var responseErrorCaptor: ArgumentCaptor<ResponseErrorListener>


    private var newsList: ArrayList<NewsDetail> = ArrayList();
    private var newsFeedDetails: NewsFeedDetails = NewsFeedDetails("", newsList)


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NewsDetailPresenter(view, reposiotry)
    }


    @Test
    fun onViewCreated() {
        presenter.onViewCreated()
        Mockito.verify(view).showProgressBar()
    }

    @Test
    fun detachView() {
        presenter.detachView()
        Mockito.verify(view).onDestroy()
    }

    @Test
    fun testloadNewsDetails() {
        presenter.loadNewsDetails()

        Mockito.verify(reposiotry).getNewsDetailsList(
            MockitoHelper.capture(responseCaptor),
            MockitoHelper.capture(responseErrorCaptor)
        )

        responseCaptor.value.onResponse(newsFeedDetails)

        Mockito.verify(view).hideProgressBar()
        Mockito.verify(view).setNewsList(newsFeedDetails.newsList)
        Mockito.verify(view).setActionBarTitle(Mockito.anyString())

    }

    // use this in place of captor.capture() for capture an argument that is not nullable
    object MockitoHelper {
        fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()
    }
}