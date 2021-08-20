package com.gr.assignment.data

data class ResponseLoginData(
    val result : String,
    val code : String,
    val message : String,
    val data : UserData
)
