package com.yelp.abrarsaair.yelpsearch.detail

import com.yelp.abrarsaair.yelpsearch.core.BaseContract
import com.yelp.abrarsaair.yelpsearch.core.network.api.NetworkHelper
import com.yelp.abrarsaair.yelpsearch.core.network.api.NetworkProvider
import com.yelp.abrarsaair.yelpsearch.core.network.api.ServiceCallback
import com.yelp.abrarsaair.yelpsearch.entities.autocomplete.Suggestions
import com.yelp.abrarsaair.yelpsearch.entities.businessdetail.Detail
import com.yelp.abrarsaair.yelpsearch.filter.Filter
import io.reactivex.android.schedulers.AndroidSchedulers


class DetailPresenter() : DetailContract.Presenter {


    val networkHelper = NetworkHelper()
    lateinit var view: DetailContract.View
    override fun loadBusinessDetail(id: String) {
        val getBusiness = NetworkProvider.instance.networkProvider.getBusinessDetail(id)
        networkHelper.serviceCall(getBusiness, object : ServiceCallback<Detail> {
            override fun onSuccess(response: Detail) {
                view.showBusinessDetail(response)
            }

            override fun onFailure(error: String, statusCode: Int) {

            }

        })
    }

    override fun attachView(view: BaseContract.View) {
        this.view = view as DetailContract.View
    }
}