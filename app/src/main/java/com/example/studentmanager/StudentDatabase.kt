package com.example.studentmanager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1)
abstract class  StudentDatabase : RoomDatabase(){
    abstract fun studentDAO() : StudentDAO

    companion object{
        @Volatile private var INSTANCE: StudentDatabase? = null

        fun getInstance(context: Context):StudentDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentDatabase::class.java,
                    "student_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}