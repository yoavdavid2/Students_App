package com.example.studentsapp

import com.example.studentsapp.adapter.StudentsAdapter
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class StudentsListViewActivity : AppCompatActivity() {

    var students: MutableList<Student>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_list_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_activity_button_one)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TODO: Step 1 - Create StudentsListViewActivity ✅
        // TODO: Step 2 - Set Launcher ✅
        // TODO: Step 3 - Set layout in xml ✅
        // TODO: Step 4 - Implement adapter ✅

//        students = Model.shared.students
//        val listView: ListView = findViewById(R.id.students_list_view)
//        listView.adapter = StudentsAdapter(students)
//
//        listView.setOnItemClickListener { parent, view, position, id ->
//            Log.d("TAG", "A new row click on cell index $position")
//        }
    }
}

