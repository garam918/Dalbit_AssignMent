package com.gr.assignment.data

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("class") val classInfo : ArrayList<LectureData>,
    @SerializedName("public") val publicClassInfo : ArrayList<PublicClassData>
)
