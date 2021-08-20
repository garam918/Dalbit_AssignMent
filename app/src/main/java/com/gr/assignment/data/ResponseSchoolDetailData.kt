package com.gr.assignment.data

data class ResponseSchoolDetailData(
    val result : String,
    val code : String,
    val message : String,
    val data : SchoolDetailData?
)
