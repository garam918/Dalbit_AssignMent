package com.gr.assignment.data

import com.google.gson.annotations.SerializedName

data class ResponseCourseContentData(
    val result : String,
    val code : String,
    val message : String,
    val data : List<CourseDetailData>
)
