package com.example.studentmanager

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Tạo danh sách sinh viên
        val items = mutableListOf<String>()

        // Thêm 50 sinh viên vào danh sách
        repeat(50) { i ->
            items.add("Student $i")
        }

        // Tạo ArrayAdapter
        val adapter = ArrayAdapter(
            this,
            R.layout.simple_item_layout,
            R.id.item_content,// Layout mặc định
            items
        )

        // Gán adapter cho ListView
        val listView: ListView = findViewById(R.id.listView)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectionItem = parent.getItemAtPosition(position) as String

            Toast.makeText(this,"Bạn đã chọn : $selectionItem",Toast.LENGTH_SHORT).show()
        }
    }
}
