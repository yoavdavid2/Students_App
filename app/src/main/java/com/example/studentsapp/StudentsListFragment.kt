package com.example.studentsapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.adapter.StudentsRecyclerAdapter
import com.example.studentsapp.databinding.FragmentStudentsListBinding
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class StudentsListFragment : Fragment() {

    private var adapter: StudentsRecyclerAdapter? = null
    private var binding: FragmentStudentsListBinding? = null

    //    private var viewModel: StudentsListViewModel? = null
    private val viewModel: StudentsListViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        viewModel = ViewModelProvider(this)[StudentsListViewModel::class.java]
    }

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
        val view = binding?.root
        getAllStudents()

        val recyclerView: RecyclerView? = binding?.recyclerView
        recyclerView?.setHasFixedSize(true)

        viewModel.students.observe(viewLifecycleOwner) {
            adapter?.students = it
            adapter?.notifyDataSetChanged()
            binding?.progressBar?.visibility = View.GONE
        }

        val layoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = layoutManager

        adapter = StudentsRecyclerAdapter(viewModel.students.value)
        adapter?.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.d("Event", "On click Activity listener position $position")
            }

            override fun onItemClick(student: Student?) {
                student?.let {
                    val action =
                        StudentsListFragmentDirections.actionStudentsListFragmentToBlueFragment(it.name)
                    view?.let {
                        Navigation.findNavController(it).navigate(action)
                    }
                }
            }
        }
        recyclerView?.adapter = adapter

        val action = StudentsListFragmentDirections.actionGlobalAddStudentFragment()
        binding?.addStudentButton?.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                action
            )
        )

        return view
    }

    private fun getAllStudents() {
        binding?.progressBar?.visibility = View.VISIBLE
        Model.shared.refreshStudents()
    }

    override fun onResume() {
        super.onResume()
//        getAllStudents()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}