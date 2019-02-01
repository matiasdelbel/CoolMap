package com.mdelbel.android.coolmap.data.place

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val requestInterface = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> createService(service: Class<T>): T = requestInterface.create(service)
}

private const val BASE_URL = "http://192.168.0.105:3000/api/"