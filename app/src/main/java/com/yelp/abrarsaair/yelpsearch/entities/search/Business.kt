package com.yelp.abrarsaair.yelpsearch.entities.search

data class Business(
        val id: String,
        val alias: String,
        val name: String,
        val image_url: String,
        val is_closed: Boolean,
        val url: String,
        val review_count: Int,
        val categories: List<Category>,
        val rating: Double,
        val coordinates: Coordinates,
        val transactions: List<String>,
        val price: String,
        val location: Location,
        val phone: String,
        val display_phone: String,
        val distance: Double
)