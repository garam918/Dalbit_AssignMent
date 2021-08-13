package com.gr.assignment.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "public_class_table")
data class PublicClassEntity(
    @PrimaryKey val name : String,
    val professor : String,
    val count : Int
)