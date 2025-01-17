package com.example.studentsapp.model

import android.graphics.Bitmap
import com.example.studentsapp.base.Constants
import com.example.studentsapp.base.EmptyCallback
import com.example.studentsapp.base.StudentsCallback
import com.example.studentsapp.base.UploadSuccessCallback
import com.google.firebase.firestore.firestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.memoryCacheSettings
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

class FirebaseModel {

    private val database = Firebase.firestore.apply {
        firestoreSettings = firestoreSettings {
            setLocalCacheSettings(memoryCacheSettings { })
        }
    }

    private val storage = Firebase.storage

//    init {
//        val settings = firestoreSettings {
//            setLoøcalCacheSettings(memoryCacheSettings { })
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
        database.collection(Constants.Collections.STUDENTS).document(student.id).delete()
            .addOnCompleteListener {
                callback()
            }
    }

    fun uploadImage(image: Bitmap, name: String, callback: UploadSuccessCallback) {
        val storageRef = storage.reference
        val imageProfileRef = storageRef.child("images/$name.jpg")

        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val uploadTask = imageProfileRef.putBytes(data)
        uploadTask
            .addOnFailureListener { callback(null) }
            .addOnSuccessListener { takeSnapshot ->
                imageProfileRef.downloadUrl.addOnSuccessListener { uri ->
                    callback(uri.toString())
                }
            }
    }
}