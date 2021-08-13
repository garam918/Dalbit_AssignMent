package com.gr.assignment.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.gr.assignment.data.ResponseData
import com.gr.assignment.database.LectureDatabase
import com.gr.assignment.database.LectureEntity
import com.gr.assignment.database.PublicClassEntity
import com.gr.assignment.network.NetworkController
import com.gr.assignment.network.NetworkService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(application: Application) {
    private val networkService : NetworkService by lazy {
        NetworkController.instance.networkService
    }

    private val lectureDatabase = LectureDatabase.getInstance(application)!!
    private val lectureDao = lectureDatabase.lectureDao()
    private val lectures : LiveData<List<LectureEntity>> = lectureDao.getLecture()
    private val classes : LiveData<List<PublicClassEntity>> = lectureDao.getPublicClass()

    fun getAllLecture() : LiveData<List<LectureEntity>> {
        return lectures
    }

    fun getAllPublicClass() : LiveData<List<PublicClassEntity>> {
        return classes
    }

    fun getLectureInfo() {
        networkService.getLectureInfo().enqueue(object : Callback<ResponseData> {
            override fun onFailure(call: Call<ResponseData>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {

                val item = response.body()!!

                repeat(item.classInfo.size) {
                    CoroutineScope(Dispatchers.IO).launch {
                        lectureDao.insertLecture(
                            LectureEntity(item.classInfo[it].name,
                                item.classInfo[it].professor,
                                item.classInfo[it].count)
                        )
                    }
                }

                repeat(item.publicClassInfo.size) {
                    CoroutineScope(Dispatchers.IO).launch {
                        lectureDao.insertPublicClass(
                            PublicClassEntity(item.publicClassInfo[it].name,
                                item.publicClassInfo[it].place,
                                item.publicClassInfo[it].count)
                        )
                    }
                }
            }
        })

    }
}