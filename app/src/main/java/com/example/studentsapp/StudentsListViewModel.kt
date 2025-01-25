package com.example.studentsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class StudentsListViewModel : ViewModel() {

    var students: LiveData<List<Student>> = Model.shared.students
}