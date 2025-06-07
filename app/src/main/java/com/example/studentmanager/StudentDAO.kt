package com.example.studentmanager

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDAO{
    @Insert
    fun insert(student: Student)

    @Query("SELECT * FROM students")
    fun getAll(): List<Student>

    @Query("DELETE FROM students WHERE mssv = :mssv")
    fun deleteByMssv(mssv:String)
}