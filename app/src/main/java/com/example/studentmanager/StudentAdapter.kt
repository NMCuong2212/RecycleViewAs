package com.example.studentmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class StudentAdapter(
    private val students: MutableList<Student>,
//    private val dbHelper: StudentDatabaseHelper
    private var studentDAO : StudentDAO
) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hoTen: TextView = itemView.findViewById(R.id.hoTen)
        val mssv: TextView = itemView.findViewById(R.id.MSSV)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.stu_content_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]
        holder.hoTen.text = student.hoTen
        holder.mssv.text = student.mssv

        holder.btnDelete.setOnClickListener {
            val studentToRemove = students[position]
//            dbHelper.deleteStudentByMssv(studentToRemove.mssv)
            Executors.newSingleThreadExecutor().execute {
                studentDAO.deleteByMssv(studentToRemove.mssv)
            }
            students.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, students.size)
        }
    }


    override fun getItemCount(): Int = students.size
}


