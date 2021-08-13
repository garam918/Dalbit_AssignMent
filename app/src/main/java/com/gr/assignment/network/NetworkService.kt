package com.gr.assignment.network

import com.gr.assignment.data.ResponseData
import retrofit2.Call
import retrofit2.http.GET

interface NetworkService {

    @GET("/coding_test.php")
    fun getLectureInfo() : Call<ResponseData>
}