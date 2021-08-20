package com.gr.assignment.ui.home

import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gr.assignment.SingleTon
import com.gr.assignment.data.*
import com.gr.assignment.network.NetworkService
import com.gr.assignment.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HomeViewModel : ViewModel() {

    private val retrofitBuilder = RetrofitBuilder

    val lectureData = ObservableArrayList<LectureData?>()
    val publicClassData = ObservableArrayList<PublicClassData>()
    val profileImage = MutableLiveData<String>().apply { this.value = "" }
    val courseData = ObservableArrayList<CourseInfoData>()

    val userToken = MutableLiveData<String>().apply {
        this.value = SingleTon.prefs.userToken
    }

    val mon = MutableLiveData<Int>().apply {
        val now = System.currentTimeMillis()
        val date = Date(now)
        val mFormat = SimpleDateFormat("MM")
        val time = mFormat.format(date)

        this.value = time.toInt()
    }

    val day = MutableLiveData<String>().apply {
        val now = System.currentTimeMillis()
        val date = Date(now)
        val mFormat = SimpleDateFormat("dd")
        val time = mFormat.format(date)

        this.value = time
    }

    fun getCourseList() {

        if(courseData.isNullOrEmpty()) {

            retrofitBuilder.networkService.getCourseList(
                userToken.value.toString(),
                userToken.value.toString()
            ).enqueue(object : Callback<ResponseCourseListData> {
                override fun onFailure(call: Call<ResponseCourseListData>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ResponseCourseListData>,
                    response: Response<ResponseCourseListData>
                ) {
                    val res = response.body()!!
                    res.data.forEach { courseInfoData ->
                        courseData.add(courseInfoData)
                    }
                }
            })
        }
    }

    fun getLectureInfo() {
        retrofitBuilder.networkService.getLectureInfo().enqueue(object : Callback<ResponseData> {
            override fun onFailure(call: Call<ResponseData>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                val res = response.body()!!
                repeat(res.classInfo.size) {
                    lectureData.add(res.classInfo[it])
                }

                repeat(res.publicClassInfo.size) {
                    publicClassData.add(res.publicClassInfo[it])
                }
            }
        })

    }
}