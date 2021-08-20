package com.gr.assignment.data

import com.google.gson.annotations.SerializedName

data class ResponseCourseListData(
    val result : String,
    val code : String,
    val message : String,
    val data : List<CourseInfoData>
)
