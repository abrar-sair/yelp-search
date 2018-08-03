package com.yelp.abrarsaair.yelpsearch.entities.search

data class SearchResults(
        val businesses: List<Business>,
        val total: Int,
        val region: Region
)