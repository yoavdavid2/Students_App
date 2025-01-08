package com.example.studentsapp.model

import android.os.Looper
import androidx.core.os.HandlerCompat
import com.example.studentsapp.base.EmptyCallback
import com.example.studentsapp.base.StudentsCallback
import com.example.studentsapp.model.dao.AppLocalDb
import com.example.studentsapp.model.dao.AppLocalDbRepository
import java.util.concurrent.Executors

class Model private constructor() {

    private val database: AppLocalDbRepository = AppLocalDb.database
    private val executor = Executors.newSingleThreadExecutor()
    private val mainHandler = HandlerCompat.createAsync(Looper.getMainLooper())

    private val firebaseModel = FirebaseModel()

    companion object {
        val shared = Model()
    }

    fun getAllStudents(callback: StudentsCallback) {
        executor.execute {
            val students = database.studentDao().getAllStudents()

            Thread.sleep(4000)

            mainHandler.post {
                callback(students)
            }
        }
    }

    fun addStudent(student: Student, callback: EmptyCallback) {
        executor.execute {
            database.studentDao().insertAll(student)

            Thread.sleep(4000)

            mainHandler.post {
                callback()
            }
        }
    }

    fun deleteStudent(student: Student, callback: EmptyCallback) {
        executor.execute {
            database.studentDao().delete(student)
            Thread.sleep(4000)
            mainHandler.post {
                callback()
            }
        }
    }
}