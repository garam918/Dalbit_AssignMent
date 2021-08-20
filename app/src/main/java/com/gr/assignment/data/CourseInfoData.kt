package com.gr.assignment.data

import com.google.gson.annotations.SerializedName

data class CourseInfoData(
    val id : Int,
    val name : String,
    val visible : String,
    @SerializedName("study_start") val studyStart : String,
    @SerializedName("study_end") val studyEnd : String
)
