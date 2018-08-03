package com.yelp.abrarsaair.yelpsearch

import com.yelp.abrarsaair.yelpsearch.entities.search.Category

object Const {
    const val REQUEST_CODE = 1001
    const val BUSINESS_ID = "BUSINESS_ID"
}

object EndPoints {
    const val API_KEY = "eXE5KEU47fhEyVdACZQ7hIhjtccnep69HUKQWhwmQ_QgNQilkbos9iZdmrzb2B39OxjL_fb3Tq1I8X-memjsLSFQhdC5f9U6ar-0LQv7GpmOBk1mMUbVae_Ee9xiW3Yx"
    const val TOKEN = "Bearer ${API_KEY}"
    const val BASE_URL = "https://api.yelp.com/v3/"
    const val SEARCH = "businesses/search"
    const val AUTOCOMPLETE = "autocomplete"
    const val BUSINESS = "businesses/{id}"
    const val BUSINESS_REVIEWS = "businesses/{id}/reviews"

}

fun convertCategory(categories: List<Category>): String{
    return  categories.map { category -> category.title }.toList().joinToString { s -> s }
}
