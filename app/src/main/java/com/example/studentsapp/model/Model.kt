package com.example.studentsapp.model

import android.graphics.Bitmap
import com.example.studentsapp.base.EmptyCallback
import com.example.studentsapp.base.StudentsCallback
import com.example.studentsapp.base.UploadErrorCallback
import com.example.studentsapp.base.UploadSuccessCallback

/*
1. Create Firebase model ✅
2. Set and Get ✅
3. Integrate Firestore ✅
4. Integrate Student ✅
 */

class Model private constructor() {

    private val firebaseModel = FirebaseModel()
    private val cloudinaryModel = CloudinaryModel()

    enum class Storage {
        FIREBASE,
        CLOUDINARY
    }

    companion object {
        val shared = Model()
    }

    fun getAllStudents(callback: StudentsCallback) {
        firebaseModel.getAllStudents(callback)
    }

    fun addStudent(student: Student, image: Bitmap?, storage: Storage, callback: EmptyCallback) {
        firebaseModel.addStudent(student) {
            image?.let {
                uploadTo(
                    storage,
                    image = image,
                    student = student,
                    callback = { uri ->
                        if (!uri.isNullOrBlank()) {
                            val student = student.copy(avatarUrl = uri)
                            firebaseModel.addStudent(student, callback)
                        } else {
                            callback()
                        }
                    },
                )
            } ?: callback()
        }
    }

    fun deleteStudent(student: Student, callback: EmptyCallback) {
        firebaseModel.deleteStudent(student, callback)
    }

    private fun uploadTo(
        storage: Storage,
        image: Bitmap,
        student: Student,
        callback: UploadSuccessCallback
    ) {
        when (storage) {
            Storage.FIREBASE -> {
                uploadImageToFirebase(image, student.id, callback)
            }

            Storage.CLOUDINARY -> {
                uploadImageToCloudinary(
                    image = image,
                    name = student.id,
                    onSuccessCallback = callback,
                    onErrorCallback = { callback(null) }
                )
            }
        }
    }

    private fun uploadImageToFirebase(
        image: Bitmap,
        name: String,
        callback: UploadSuccessCallback
    ) {
        firebaseModel.uploadImage(image, name, callback)
    }

    fun uploadImageToCloudinary(
        image: Bitmap,
        name: String,
        onSuccessCallback: UploadSuccessCallback,
        onErrorCallback: UploadErrorCallback
    ) {
        cloudinaryModel.uploadImage(
            bitmap = image,
            name = name,
            onSuccess = onSuccessCallback,
            onError = onErrorCallback
        )
    }
}