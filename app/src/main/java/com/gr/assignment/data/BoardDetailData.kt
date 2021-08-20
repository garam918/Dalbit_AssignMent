package com.gr.assignment.data

import com.google.gson.annotations.SerializedName

data class BoardDetailData(
    val id : Int,
    val subject : String,
    val content : String,
    @SerializedName("contents_id") val contentsId : String,
    @SerializedName("user_id") val userId : String,
    @SerializedName("user_name") val userName : String,
    val picture : String,
    @SerializedName("created_at") val createdAt : String,
    @SerializedName("updated_at") val updatedAt : String
)
