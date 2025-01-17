package com.example.studentsapp.base

import com.example.studentsapp.model.Student

typealias StudentsCallback = (List<Student>) -> Unit
typealias EmptyCallback = () -> Unit
typealias UploadSuccessCallback = (String?) -> Unit
typealias UploadErrorCallback = (String?) -> Unit

object Constants {

    object Collections {
        const val STUDENTS = "students"
    }
}