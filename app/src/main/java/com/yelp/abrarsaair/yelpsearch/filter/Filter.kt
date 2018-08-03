package com.yelp.abrarsaair.yelpsearch.filter

import android.content.Context
import com.yelp.abrarsaair.yelpsearch.core.location.LocationProvider

class Filter {

    enum class SearchType(val key: Number) {
        Name(1),
        Address(2),
        Type(3)
    }
    enum class SortType(val key: Number) {
        distance(1),
        rating(2),
        best_match(3)
    }

   var searchType: SearchType = SearchType.Name
    var sortType: SortType = SortType.best_match

    companion object {
        private var instance: Filter = Filter()
        @Synchronized
        fun getInstance(): Filter {
            return instance
        }
    }

}