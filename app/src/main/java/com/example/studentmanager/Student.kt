package com.example.studentmanager

import androidx.room.Entity
import androidx.room.PrimaryKey


//data class  Student(
//    var id: Int,
//    var hoTen : String,
//    var mssv : String
//)
@Entity(tableName = "students" )
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val hoTen: String,
    val mssv: String
)

