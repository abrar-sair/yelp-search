package com.yelp.abrarsaair.yelpsearch

import com.nhaarman.mockito_kotlin.verify
import com.yelp.abrarsaair.yelpsearch.core.network.api.NetworkHelper
import com.yelp.abrarsaair.yelpsearch.core.network.api.NetworkProvider
import com.yelp.abrarsaair.yelpsearch.core.network.api.ServiceCallback
import com.yelp.abrarsaair.yelpsearch.entities.autocomplete.Suggestions
import com.yelp.abrarsaair.yelpsearch.entities.businessdetail.Detail
import com.yelp.abrarsaair.yelpsearch.entities.businessdetail.Location
import com.yelp.abrarsaair.yelpsearch.search.SearchContract
import com.yelp.abrarsaair.yelpsearch.search.SearchPresenter
import io.reactivex.Single
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.*
import org.mockito.Mockito.*
import retrofit2.Response

class SearchPresenterTest {
    @Mock
    lateinit var detail: Detail
    @Mock
    lateinit var location: android.location.Location
    @Mock
    lateinit var call: Single<Response<Suggestions>>
    @Mock
    lateinit var suggestions: Suggestions
    @Mock
    lateinit var view: SearchContract.View
    @Mock
    lateinit var networkHelper: NetworkHelper
    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<ServiceCallback<Suggestions>>
    lateinit var presenter: SearchPresenter
    lateinit var networkProvider: NetworkProvider

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        networkProvider = NetworkProvider.instance
        presenter = SearchPresenter()
        presenter.attachView(view)
    }

    @Test
    fun getBusinessTest() {

    }

    @Test
    fun loadSuggestionsTest() {
        `when`(networkProvider.networkProvider
                .getSuggestions("","0",
                        "0")).thenReturn(call)
        location.latitude = 0.0
        location.longitude = 0.0
        presenter.loadSuggestions("",location)
        verify(networkHelper).serviceCall(com.nhaarman.mockito_kotlin.eq(networkProvider.networkProvider.getSuggestions("","0",
                "0")),com.nhaarman.mockito_kotlin.capture(argumentCaptor));
        argumentCaptor.value.onSuccess(suggestions)
        verify(view).updateSuggestions(Mockito.anyList())
    }
}
