package com.gr.assignment.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val baseUrl = "https://alia.dalbitsoft.com"

    val gson = GsonBuilder().setLenient().create()

    fun getRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val networkService: NetworkService = getRetrofit().create(NetworkService::class.java)
}