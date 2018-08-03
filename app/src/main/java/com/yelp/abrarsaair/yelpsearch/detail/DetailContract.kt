package com.yelp.abrarsaair.yelpsearch.detail

import com.yelp.abrarsaair.yelpsearch.core.BaseContract
import com.yelp.abrarsaair.yelpsearch.entities.businessdetail.Detail

interface DetailContract {
    interface View: BaseContract.View {
        fun showBusinessDetail(detail: Detail)
    }

    interface Presenter: BaseContract.Presenter{
        fun loadBusinessDetail(id: String)
    }
}