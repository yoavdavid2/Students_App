package com.example.studentsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.OnItemClickListener
import com.example.studentsapp.databinding.StudentListRowBinding
import com.example.studentsapp.model.Student

class StudentsRecyclerAdapter(private var students: List<Student>?) :
    RecyclerView.Adapter<StudentViewHolder>() {
    var listener: OnItemClickListener? = null

    fun update(students: List<Student>?) {
        this.students = students
    }

    override fun getItemCount(): Int = students?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StudentListRowBinding.inflate(inflater, parent, false)

        return StudentViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(
            student = students?.get(position),
            position = position
        )
    }
}