package com.yelp.abrarsaair.yelpsearch.core.network.api

import com.yelp.abrarsaair.yelpsearch.EndPoints

import java.util.concurrent.TimeUnit

import assignment.vendawais.twittersearchapi.api.YelpGateway
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkProvider private constructor() {

    val networkProvider: YelpGateway

    init {
        val okHttpClient = provideOkHttpClient()
        val retrofit = provideRetrofit(okHttpClient, proivdeGsonConverterFactory(), provideRxJavaCallAdapterFactory())
        networkProvider = provideTweetsGateway(retrofit)
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()
    }

    private fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    private fun proivdeGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }


    private fun provideRetrofit(client: OkHttpClient, converterFactory: GsonConverterFactory, adapterFactory: RxJava2CallAdapterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(EndPoints.BASE_URL)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build()
    }


    private fun provideTweetsGateway(retrofit: Retrofit): YelpGateway {
        return retrofit.create(YelpGateway::class.java)
    }

    companion object {
        val instance = NetworkProvider()
    }
}
