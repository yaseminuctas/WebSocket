package com.yaseminuctas.betbullcase.data.network
import com.yaseminuctas.betbullcase.util.Const
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import com.yaseminuctas.betbullcase.data.model.MockData
import retrofit2.Call


interface Api {

    @GET("emredirican/mock-api/db")
    fun getData(): Call<MockData>


}