package com.example.studentsapp.model

import com.example.studentsapp.base.Constants
import com.example.studentsapp.base.EmptyCallback
import com.example.studentsapp.base.StudentsCallback
import com.google.firebase.firestore.firestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.memoryCacheSettings
import com.google.firebase.ktx.Firebase

class FirebaseModel {

    private val database = Firebase.firestore.apply {
        firestoreSettings = firestoreSettings {
            setLocalCacheSettings(memoryCacheSettings {  })
        }
    }

//    init {
//        val settings = firestoreSettings {
//            setLocalCacheSettings(memoryCacheSettings { })
//        }
//        database.firestoreSettings = settings
//    }

    fun getAllStudents(callback: StudentsCallback) {
        database.collection(Constants.Collections.STUDENTS).get().addOnCompleteListener {
            when (it.isSuccessful) {
                true -> {
                    val students: MutableList<Student> = mutableListOf()
                    for (json in it.result) {
                        students.add(Student.fromJson(json.data))
                    }
                    callback(students)
                }

                false -> callback(listOf())
            }
        }
    }

    fun addStudent(student: Student, callback: EmptyCallback) {
        database.collection(Constants.Collections.STUDENTS).document(student.id).set(student.json)
            .addOnCompleteListener {
                callback()
            }
    }

    fun deleteStudent(student: Student, callback: EmptyCallback) {

    }
}