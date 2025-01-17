package com.example.studentsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.example.studentsapp.R
import com.example.studentsapp.model.Student

class StudentsAdapter(private val students: MutableList<Student>?) : BaseAdapter() {
    override fun getCount(): Int = students?.size ?: 0

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(position: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(parent?.context)

        val view = convertView ?: inflater.inflate(R.layout.student_list_row, parent, false).apply {
            findViewById<CheckBox>(R.id.checkBox).apply {
                setOnClickListener {
                    (tag as? Int)?.let { tag ->
                        val student = students?.get(tag)
                        student?.isChecked = (it as? CheckBox)?.isChecked ?: false
                    }
                }
            }
        }

//            var view = convertView
//            if (convertView == null) {
//                view = inflater.inflate(R.layout.student_list_row, parent, false)
//                val studentCheckBox: CheckBox? = view?.findViewById(R.id.student_row_check_box)
//
//                studentCheckBox?.apply {
//                    setOnClickListener {
//                        (tag as? Int)?.let { tag ->
//                            val student = students?.get(tag)
//                            student?.isChecked = (it as? CheckBox)?.isChecked ?: false
//                        }
//                    }
//                }
//            }

        val student = students?.get(position)
        val nameTextView: TextView? = view?.findViewById(R.id.nameTextView)
        val idTextView: TextView? = view?.findViewById(R.id.idTextView)
        val studentCheckBox: CheckBox? = view?.findViewById(R.id.checkBox)

        nameTextView?.text = student?.name
        idTextView?.text = student?.id

        studentCheckBox?.apply {
            isChecked = student?.isChecked ?: false
            tag = position
        }

        return view!!
    }
}