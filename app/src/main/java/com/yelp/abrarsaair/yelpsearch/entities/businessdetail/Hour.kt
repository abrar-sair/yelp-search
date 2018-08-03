package com.yelp.abrarsaair.yelpsearch.entities.businessdetail

data class Hour(
        val hours_type: String,
        val open: List<Open>,
        val is_open_now: Boolean
)