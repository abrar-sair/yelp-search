package com.yelp.abrarsaair.yelpsearch.core.network.api


import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response


interface ServiceCallback<T> {
    fun onSuccess(response: T)
    fun onFailure(error: String, statusCode: Int = 0)
}

class NetworkHelper {

    var disposable: Disposable? = null

    fun <T> serviceCall(call: Single<Response<T>>, callback: ServiceCallback<T>) {

        call.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Response<T>> {
                    override fun onSuccess(response: Response<T>) = if (response.isSuccessful) {
                        response.body()?.let {
                            callback.onSuccess(it)
                        } ?: run {
                            callback.onFailure(response.message().toString(), response.code())
                        }
                    } else {
                        response.errorBody()?.let {
                            callback.onFailure("")
                        } ?: run {
                            callback.onFailure("")
                        }
                    }

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onError(e: Throwable) {
                        callback.onFailure("")
                    }
                })
    }

    fun dispose() {
        disposable?.let {
            if (!it.isDisposed)
                it.dispose()
        }
    }
}
