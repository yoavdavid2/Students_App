package com.example.studentsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.studentsapp.databinding.FragmentAddStudentBinding
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class AddStudentFragment : Fragment() {

    private var binding: FragmentAddStudentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddStudentBinding.inflate(inflater, container, false)

        binding?.saveButton?.setOnClickListener(::onSaveClick)
        binding?.cancelButton?.setOnClickListener(::onCancelClick)

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun onSaveClick(view: View) {
        val student = Student(
            id = binding?.idEditText?.text?.toString() ?: "",
            name = binding?.nameEditText?.text?.toString() ?: "",
            avatarUrl = "",
            isChecked = false
        )

        binding?.progressBar?.visibility = View.VISIBLE

        Model.shared.addStudent(student) {
            Navigation.findNavController(view).popBackStack()
        }
    }

    private fun onCancelClick(view: View) {
        Navigation.findNavController(view).popBackStack()
    }

}