package com.yelp.abrarsaair.yelpsearch.search

import android.location.Location
import com.yelp.abrarsaair.yelpsearch.core.BaseContract
import com.yelp.abrarsaair.yelpsearch.core.network.api.NetworkHelper
import com.yelp.abrarsaair.yelpsearch.core.network.api.NetworkProvider
import com.yelp.abrarsaair.yelpsearch.core.network.api.ServiceCallback
import com.yelp.abrarsaair.yelpsearch.entities.autocomplete.Suggestions
import com.yelp.abrarsaair.yelpsearch.entities.search.SearchResults
import com.yelp.abrarsaair.yelpsearch.filter.Filter
import io.reactivex.Single
import retrofit2.Response

class SearchPresenter() : SearchContract.Presenter {


    lateinit var view: SearchContract.View
    val networkHelper = NetworkHelper()
    override fun loadBusiness(query: String, location: Location) {

        val sortType = Filter.getInstance().sortType.toString()

        var getBusiness: Single<Response<SearchResults>>
        if (Filter.SearchType.Address == Filter.getInstance().searchType) {
            getBusiness = NetworkProvider.instance.networkProvider.searchBusinessWithAddress(query, query, sortType)
        } else {
            getBusiness = NetworkProvider.instance.networkProvider.searchBusiness(query, "37.786882", "-122.399972", sortType)
        }
        networkHelper.serviceCall(getBusiness, object : ServiceCallback<SearchResults> {
            override fun onSuccess(response: SearchResults) {
                view.showSearchResults(response.businesses)
                view.hideProgress()
            }

            override fun onFailure(error: String, statusCode: Int) {
                // view.showError(error)
            }

        })
    }


    override fun loadSuggestions(query: String, location: Location) {
        val suggestions = NetworkProvider.instance.networkProvider.getSuggestions(query, location.latitude.toString(), location.longitude.toString())
        networkHelper.serviceCall(suggestions, object : ServiceCallback<Suggestions> {
            override fun onSuccess(suggestions: Suggestions) {
                if (Filter.SearchType.Address == Filter.getInstance().searchType && suggestions.businesses.isNotEmpty()) {
                    view.updateSuggestions(suggestions.businesses)
                } else {
                    view.updateSuggestions(suggestions.terms)
                }
            }

            override fun onFailure(error: String, statusCode: Int) {
                view.hideProgress()
            }

        })
    }

    override fun attachView(view: BaseContract.View) {
        this.view = view as SearchContract.View
    }
}