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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_list)

        studentList.add(Student("Nguyen Manh Cuong","20225268"))
        studentList.add(Student("Dong Van The","20225408"))
        studentList.add(Student("Le Hong Anh","20225268"))

        val adapter = StudentAdapter(studentList)
        val recyclerView =findViewById<RecyclerView>(R.id.recycleview_stu)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val editHoten = findViewById<EditText>(R.id.edit_hoten)
        val editMssv = findViewById<EditText>(R.id.edit_mssv)

        findViewById<Button>(R.id.btn_add).setOnClickListener{
            val name = editHoten.text.toString()
            val mssv = editMssv.text.toString()

            if(name.isNotEmpty() && mssv.isNotEmpty()){
                studentList.add(Student(name,mssv))
                adapter.notifyItemInserted(studentList.size-1)

            }
        }



        }


}