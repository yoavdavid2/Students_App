package com.example.studentsapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.studentsapp.model.Student

@Dao
interface StudentDao {

    @Query("SELECT * FROM Student")
    fun getAllStudents(): LiveData<List<Student>>

    @Query("SELECT * FROM Student WHERE id =:id")
    fun getStudentById(id: String): Student

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg student: Student)

    @Delete
    fun delete(student: Student)
}