package com.example.studentmanager

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StudentActivity : AppCompatActivity() {
    private var studentList = mutableListOf<Student>()
    private lateinit var dbHelper :StudentDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_list)

        dbHelper = StudentDatabaseHelper(this)

        studentList = dbHelper.getAllStudents().toMutableList()

        val adapter = StudentAdapter(studentList,dbHelper)
        val recyclerView =findViewById<RecyclerView>(R.id.recycleview_stu)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val editHoten = findViewById<EditText>(R.id.edit_hoten)
        val editMssv = findViewById<EditText>(R.id.edit_mssv)

        findViewById<Button>(R.id.btn_add).setOnClickListener{
            val name = editHoten.text.toString()
            val mssv = editMssv.text.toString()

            if(name.isNotEmpty() && mssv.isNotEmpty()){
                val newStudent = Student(0,name,mssv)
                dbHelper.insertStudent(name,mssv)

                studentList.add(newStudent)
                adapter.notifyItemInserted(studentList.size-1)

                editMssv.text.clear()
                editHoten.text.clear()
            }
        }
        dbHelper.logAllStudents()



        }


}