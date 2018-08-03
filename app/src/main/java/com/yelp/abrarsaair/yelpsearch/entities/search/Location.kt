package com.yelp.abrarsaair.yelpsearch.entities.search

data class Location(
        val address1: String,
        val address2: String,
        val address3: String,
        val city: String,
        val zip_code: String,
        val country: String,
        val state: String,
        val display_address: List<String>
)