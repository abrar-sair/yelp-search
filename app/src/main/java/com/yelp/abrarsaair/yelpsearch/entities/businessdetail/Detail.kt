package com.yelp.abrarsaair.yelpsearch.entities.businessdetail

import com.yelp.abrarsaair.yelpsearch.entities.search.Category

data class Detail(
        val id: String,
        val alias: String,
        val name: String,
        val image_url: String,
        val is_claimed: Boolean,
        val is_closed: Boolean,
        val url: String,
        val price: String,
        val rating: Double,
        val review_count: Int,
        val phone: String,
        val display_phone: String,
        val photos: List<String>,
        val hours: List<Hour>,
        val categories: List<Category>,
        val coordinates: Coordinates,
        val location: Location,
        val transactions: List<String>
)