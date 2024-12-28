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

class AddStudentFragment : Fragment() {

    var saveButton: Button? = null
    var cancelButton: Button? = null
    var nameEditText: EditText? = null
    var idEditText: EditText? = null
    var savedMessageTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_student, container, false)

        setupView(view)

        saveButton?.setOnClickListener(::onSaveClick)
        cancelButton?.setOnClickListener(::onCancelClick)

        return view
    }

    private fun setupView(view: View?) {
        saveButton = view?.findViewById(R.id.add_student_activity_save_button)
        cancelButton = view?.findViewById(R.id.add_student_activity_cancel_button)
        nameEditText = view?.findViewById(R.id.add_student_activity_name_edit_text)
        idEditText = view?.findViewById(R.id.add_student_activity_id_edit_text)
        savedMessageTextView = view?.findViewById(R.id.add_student_activity_save_message_text_view)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun onSaveClick(view: View) {
        // TODO:
    }

    private fun onCancelClick(view: View) {
        Navigation.findNavController(view).popBackStack()
    }

}