package com.hadi.superherolexicon.network

import com.hadi.superherolexicon.data.model.Item
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("getSampleJson")
    suspend fun getDashboard(): List<Item>

    @GET("data-random.json")
    suspend fun getRandomDashboard(): List<Item>

}

object NetworkClient {

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)

    val service: ApiService by lazy {
        retrofit.baseUrl("http://192.168.29.70:8080/flourmill/")
            .build().create(ApiService::class.java)
    }

}