package com.gr.assignment.data

data class ResponseBoardData(
    val result : String,
    val code : String,
    val message : String,
    val data : List<BoardData>
)
