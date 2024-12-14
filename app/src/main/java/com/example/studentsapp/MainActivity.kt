package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Log.*
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TODO: Step 1 - Add Student Button ✅
        // TODO: Step 2 - Navigate to AddStudentActivity ✅
        // TODO: Step 3 - Create AddStudentLayout ✅
        // TODO: Step 4 - Save Student ✅

//        class Listener : View.OnClickListener {
//            override fun onClick(v: View?) {
//            }
//        }

        val addStudentButton: Button = findViewById(R.id.main_activity_add_student_button)

//        // Option 1
//        val listener = Listener()
//        addStudentButton.setOnClickListener(listener)
//
//        // Option 2
//        addStudentButton.setOnClickListener(object: View.OnClickListener {
//            override fun onClick(v: View?) {
//                Log.d("Activity", "onClick")
//            }
//        })

        // Option 3

        addStudentButton.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
    }
}