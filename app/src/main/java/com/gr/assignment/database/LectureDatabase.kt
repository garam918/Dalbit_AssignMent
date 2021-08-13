package com.gr.assignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LectureEntity::class, PublicClassEntity::class], version = 1)
abstract class LectureDatabase : RoomDatabase() {
    abstract fun lectureDao() : LectureDao

    companion object {
        private var instance : LectureDatabase? = null

        @Synchronized
        fun getInstance(context: Context) : LectureDatabase? {
            if(instance == null) {
                synchronized(LectureDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LectureDatabase::class.java,
                        "lecture-database"
                    ).build()
                }
            }
            return instance
        }
    }
}