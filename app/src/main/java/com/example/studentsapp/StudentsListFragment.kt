package com.example.studentsapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.adapter.StudentsRecyclerAdapter
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class StudentsListFragment : Fragment() {
    var students: MutableList<Student>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_students_list, container, false)
        students = Model.shared.students

        val recyclerView: RecyclerView =
            view.findViewById(R.id.students_list_fragment_recycler_view)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        val adapter = StudentsRecyclerAdapter(students)
        adapter.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.d("Event", "On click Activity listener position $position")
            }

            override fun onItemClick(student: Student?) {
                student?.let {
                    val action =
                        StudentsListFragmentDirections.actionStudentsListFragmentToBlueFragment(it.name)
                    Navigation.findNavController(view).navigate(action)
                }
            }
        }
        recyclerView.adapter = adapter

        val imageButton: ImageButton? = view?.findViewById(R.id.students_list_add_student_button)

        val action = StudentsListFragmentDirections.actionGlobalAddStudentFragment()
        imageButton?.setOnClickListener(Navigation.createNavigateOnClickListener(action))

        return view
    }
}