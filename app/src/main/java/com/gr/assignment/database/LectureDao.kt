package com.gr.assignment.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LectureDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLecture(lecture : LectureEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPublicClass(publicClass : PublicClassEntity)

    @Query("SELECT * FROM lecture_table")
    fun getLecture() : LiveData<List<LectureEntity>>

    @Query("SELECT * FROM public_class_table")
    fun getPublicClass() : LiveData<List<PublicClassEntity>>
}