package com.example.studentsapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentsapp.adapter.StudentsRecyclerAdapter
import com.example.studentsapp.databinding.FragmentStudentsListBinding
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class StudentsListFragment : Fragment() {

    private var students: List<Student>? = null
    private var adapter: StudentsRecyclerAdapter? = null

    private var binding: FragmentStudentsListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // TODO: Set DB ✅
        // TODO: Refactor Model to support local DB ✅
        // TODO: Refactor fragment to work with live data ✅
        // TODO: Add progress indicator for better UX ✅
        // TODO: Migrate to ViewBinding ✅

        binding = FragmentStudentsListBinding.inflate(inflater, container, false)

        binding?.recyclerView?.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        binding?.recyclerView?.layoutManager = layoutManager

        adapter = StudentsRecyclerAdapter(students)
        adapter?.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.d("Event", "On click Activity listener position $position")
            }

            override fun onItemClick(student: Student?) {
                student?.let {
                    val action =
                        StudentsListFragmentDirections.actionStudentsListFragmentToBlueFragment(it.name)
                    binding?.root?.let {
                        Navigation.findNavController(it).navigate(action)
                    }
                }
            }
        }
        binding?.recyclerView?.adapter = adapter

        val action = StudentsListFragmentDirections.actionGlobalAddStudentFragment()
        binding?.addStudentButton?.setOnClickListener(Navigation.createNavigateOnClickListener(action))

        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        getAllStudents()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun getAllStudents() {
        binding?.progressBar?.visibility = View.VISIBLE

        Model.shared.getAllStudents {
            students = it
            adapter?.update(it)
            adapter?.notifyDataSetChanged()

            binding?.progressBar?.visibility = View.GONE
        }
    }
}