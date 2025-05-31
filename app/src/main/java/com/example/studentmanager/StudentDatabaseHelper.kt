package com.example.studentmanager

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class StudentDatabaseHelper (context: Context): SQLiteOpenHelper(
    context,DATABASE_NAME,null, DATABASE_VERSION
){
    companion object{
        private const val DATABASE_NAME = "student_db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "students"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_MSSV = "mssv"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
            CREATE TABLE $TABLE_NAME(
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_NAME TEXT NOT NULL,
            $COLUMN_MSSV TEXT NOT NULL
            );
        """.trimIndent()
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertStudent(hoTen: String, mssv: String) {
        val db = writableDatabase
        val sql = "INSERT INTO $TABLE_NAME ($COLUMN_NAME, $COLUMN_MSSV) VALUES (?, ?)"
        db.execSQL(sql, arrayOf(hoTen, mssv))
        db.close()
    }

    fun deleteStudentByMssv(mssv: String) {
        val db = writableDatabase
        val sql = "DELETE FROM $TABLE_NAME WHERE $COLUMN_MSSV = ?"
        db.execSQL(sql, arrayOf(mssv))
        db.close()
    }

    fun getAllStudents(): MutableList<Student>{
        val studentList = mutableListOf<Student>()
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME",null)

        if(cursor.moveToFirst()){
            do{
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val mssv = cursor.getString(2)
                studentList.add(Student(id,name,mssv))
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return studentList

    }

    fun logAllStudents() {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM students", null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val mssv = cursor.getString(2)
            Log.d("DB_LOG", "ID: $id, Name: $name, MSSV: $mssv")
        }
        cursor.close()
        db.close()
    }





}