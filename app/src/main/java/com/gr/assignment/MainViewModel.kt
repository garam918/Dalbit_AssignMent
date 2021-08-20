package com.gr.assignment

import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gr.assignment.data.*
import com.gr.assignment.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val retrofitBuilder = RetrofitBuilder

    val userId = MutableLiveData<String>()
    val userPw = MutableLiveData<String>()

    val schoolData = MutableLiveData<ArrayList<SchoolInfoData>>().apply {
        this.value = arrayListOf(SchoolInfoData(0,"",""))
    }

    val schoolsName = MutableLiveData<ArrayList<String>>().apply {
        this.value = arrayListOf("학교를 선택해주세요")
    }

    val currentSelectedSchool = MutableLiveData<SchoolDetailData>()

    val currentSelectedSchoolName = MutableLiveData<String>().apply {
        this.value = ""
    }

    val currentSelectedSchoolId = MutableLiveData<Int>().apply {
        this.value = 0
    }

    fun getSchoolList() {
        retrofitBuilder.networkService.getSchoolList().enqueue(object : Callback<ResponseSchoolListData> {
            override fun onFailure(call: Call<ResponseSchoolListData>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ResponseSchoolListData>,
                response: Response<ResponseSchoolListData>
            ) {
                val res = response.body()!!
                val message = res.message
                if(message == "success") {
                    repeat(res.data.size) {
                        schoolData.value?.add(it, SchoolInfoData(res.data[it].id,
                                res.data[it].name,res.data[it].logoUrl))
                        schoolsName.value?.add(res.data[it].name)
                    }
                }
            }
        })

    }

    fun getSchoolDetailData(id : Int) {
        retrofitBuilder.networkService.getSchoolDetail(id).enqueue(object : Callback<ResponseSchoolDetailData> {
            override fun onFailure(call: Call<ResponseSchoolDetailData>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ResponseSchoolDetailData>,
                response: Response<ResponseSchoolDetailData>
            ) {
                val res = response.body()!!
                val data = res.data

                if(res.message == "success") {
                    currentSelectedSchool.value =
                        SchoolDetailData(data!!.id, data.name, data.logoUrl, data.token)

                    Log.e("제발 돼라", currentSelectedSchool.value.toString())
                }
            }
        })

    }

    val spinnerClickListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            currentSelectedSchoolName.value = parent?.getItemAtPosition(position).toString()

            if (schoolData.value?.size!! >= 2 && position != 0) {
                currentSelectedSchoolId.value = schoolData.value!![position - 1].id
                getSchoolDetailData(currentSelectedSchoolId.value!!)
            }
        }
        override fun onNothingSelected(p0: AdapterView<*>?) {

        }
    }

}