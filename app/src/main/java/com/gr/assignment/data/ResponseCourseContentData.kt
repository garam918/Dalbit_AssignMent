package com.gr.assignment.data

data class ResponseCourseContentData(
    val result : String,
    val code : String,
    val message : String,
    val data : List<CourseDetailData>
)
