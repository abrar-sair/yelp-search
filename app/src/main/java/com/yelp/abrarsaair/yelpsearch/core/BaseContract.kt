package com.yelp.abrarsaair.yelpsearch.core

interface BaseContract {
    interface View {
        fun showError(error: Error)
        fun init()
    }

    interface Presenter {
        fun attachView( view: View)
    }
}