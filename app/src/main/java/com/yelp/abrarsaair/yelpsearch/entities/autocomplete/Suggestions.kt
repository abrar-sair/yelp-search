package com.yelp.abrarsaair.yelpsearch.entities.autocomplete

import com.yelp.abrarsaair.yelpsearch.entities.search.Category

data class Suggestions(
        val categories: List<Category>,
        val businesses:  List<Business>,
        val terms: List<Term>
)