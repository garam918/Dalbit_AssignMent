package com.gr.assignment.data

import com.google.gson.annotations.SerializedName

data class CourseDetailData(
    val id : Int,
    val name : String,
    @SerializedName("course_id")val courseId : String,
    @SerializedName("modname")val modName : String
)
