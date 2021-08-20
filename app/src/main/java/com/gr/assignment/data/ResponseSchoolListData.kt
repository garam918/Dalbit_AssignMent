package com.gr.assignment.data

data class ResponseSchoolListData(
    val result : String,
    val code : String,
    val message : String,
    val data : List<SchoolInfoData>
)
