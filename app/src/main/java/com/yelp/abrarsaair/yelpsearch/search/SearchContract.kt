package com.yelp.abrarsaair.yelpsearch.search

import android.location.Location
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.yelp.abrarsaair.yelpsearch.core.BaseContract
import com.yelp.abrarsaair.yelpsearch.entities.search.Business
import com.yelp.abrarsaair.yelpsearch.entities.search.SearchResults

interface SearchContract {
    interface View : BaseContract.View {
        fun updateSuggestions(result: List<SearchSuggestion>)
        fun showSearchResults(result: List<Business>)
        fun hideProgress()
        fun showDetail(business: Business)
    }

    interface Presenter : BaseContract.Presenter {
        fun loadSuggestions(query: String, location: Location)
        fun loadBusiness(query: String, location: Location)
    }
}