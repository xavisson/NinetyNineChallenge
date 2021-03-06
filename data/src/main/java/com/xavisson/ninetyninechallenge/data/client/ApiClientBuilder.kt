package com.xavisson.ninetyninechallenge.data.client

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.xavisson.ninetyninechallenge.data.adapter.ErrorCallAdapterFactory
import com.xavisson.ninetyninechallenge.data.adapter.RxErrorCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

class ApiClientBuilder(apiClient: NinetyNineApiClient, converterFactoryParam: GsonConverterFactory? = null) {
    companion object {
        private const val BASE_URL: String = "https://dev.ninetynine.com/"
    }

    private val gsonBuilder: Gson = GsonBuilder().create()

    private val converterFactory = converterFactoryParam ?: GsonConverterFactory.create(gsonBuilder)

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(apiClient.getClient())
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxErrorCallAdapterFactory.create())
            .addCallAdapterFactory(ErrorCallAdapterFactory.create())
            .build()

    fun <T> buildEndpoint(apiClass: Class<T>): T {
        return retrofit.create(apiClass)
    }

    fun <T : Any> buildEndpoint(apiClass: KClass<T>): T {
        return retrofit.create(apiClass.java)
    }
}