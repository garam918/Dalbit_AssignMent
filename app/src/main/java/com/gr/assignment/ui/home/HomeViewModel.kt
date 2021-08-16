package com.gr.assignment.ui.home

import android.icu.text.SimpleDateFormat
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gr.assignment.data.LectureData
import com.gr.assignment.data.PublicClassData
import com.gr.assignment.data.ResponseData
import com.gr.assignment.network.NetworkController
import com.gr.assignment.network.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HomeViewModel : ViewModel() {

    private val networkService : NetworkService by lazy {
        NetworkController.instance.networkService
    }

    val lectureData = ObservableArrayList<LectureData?>()
    val publicClassData = ObservableArrayList<PublicClassData>()
    val profileImage = MutableLiveData<String>().apply { this.value = "" }

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

    fun getLectureInfo() {
        networkService.getLectureInfo().enqueue(object : Callback<ResponseData> {
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