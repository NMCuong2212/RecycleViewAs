package com.example.studentmanager

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executors

class StudentActivity : AppCompatActivity() {
    private var studentList = mutableListOf<Student>()
//    private lateinit var dbHelper :StudentDatabaseHelper
    private lateinit var studentDAO: StudentDAO
    private lateinit var adapter: StudentAdapter

    private val executor = Executors.newSingleThreadExecutor()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_list)

        studentDAO = StudentDatabase.getInstance(applicationContext).studentDAO()

        // Tạo adapter với list rỗng lúc đầu
        adapter = StudentAdapter(studentList, studentDAO)

        val recyclerView = findViewById<RecyclerView>(R.id.recycleview_stu)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val editHoten = findViewById<EditText>(R.id.edit_hoten)
        val editMssv = findViewById<EditText>(R.id.edit_mssv)

        // Load dữ liệu Room trong background thread
        executor.execute {
            val data = studentDAO.getAll()
            runOnUiThread {
                studentList.clear()
                studentList.addAll(data)
                adapter.notifyDataSetChanged()
            }
        }

        findViewById<Button>(R.id.btn_add).setOnClickListener {
            val name = editHoten.text.toString()
            val mssv = editMssv.text.toString()

            if (name.isNotEmpty() && mssv.isNotEmpty()) {
                executor.execute {
                    val student = Student(hoTen = name, mssv = mssv)
                    studentDAO.insert(student)

                    val updatedList = studentDAO.getAll()
                    runOnUiThread {
                        studentList.clear()
                        studentList.addAll(updatedList)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }



}