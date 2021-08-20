package com.gr.assignment.data

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("user_id") val userId : String,
    val name : String,
    val token : String
)
