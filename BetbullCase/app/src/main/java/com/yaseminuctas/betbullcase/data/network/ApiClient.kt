package com.yaseminuctas.betbullcase.data.network

import com.yaseminuctas.betbullcase.util.Const
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    fun getApiService(token: String?): Api {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient(token))
            .build()
        return retrofitBuilder.create(Api::class.java)
    }
    private fun getOkHttpClient(token: String?): OkHttpClient {
        val client =OkHttpClient.Builder()
        client.addInterceptor(RequestInterceptor(token))
        return client.build()
    }
}