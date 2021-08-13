package com.gr.assignment.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lecture_table")
data class LectureEntity(
    @PrimaryKey val name : String,
    val professor : String,
    val count : Int
)