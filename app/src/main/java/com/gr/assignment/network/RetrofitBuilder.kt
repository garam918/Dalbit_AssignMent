package com.gr.assignment.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val baseUrl = "https://alia.dalbitsoft.com"

    fun getRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val networkService: NetworkService = getRetrofit().create(NetworkService::class.java)
}