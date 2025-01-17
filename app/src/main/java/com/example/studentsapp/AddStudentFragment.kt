package com.example.studentsapp

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.studentsapp.databinding.FragmentAddStudentBinding
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class AddStudentFragment : Fragment() {

    private var cameraLauncher: ActivityResultLauncher<Void?>? = null

    private var binding: FragmentAddStudentBinding? = null
    private var didSetProfileImage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddStudentBinding.inflate(inflater, container, false)

        binding?.saveButton?.setOnClickListener(::onSaveClick)
        binding?.cancelButton?.setOnClickListener(::onCancelClick)

        cameraLauncher =
            registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
                binding?.imageView?.setImageBitmap(bitmap)
                didSetProfileImage = true
            }

        binding?.takePictureButton?.setOnClickListener {
            cameraLauncher?.launch(null)
        }

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun onSaveClick(view: View) {
        val student = Student(
            id = binding?.idEditText?.text?.toString() ?: "",
            name = binding?.nameEditText?.text?.toString() ?: "",
            avatarUrl = "",
            isChecked = false
        )

        binding?.progressBar?.visibility = View.VISIBLE

        if (didSetProfileImage) {
            binding?.imageView?.isDrawingCacheEnabled = true
            binding?.imageView?.buildDrawingCache()
            val bitmap = (binding?.imageView?.drawable as BitmapDrawable).bitmap

            Model.shared.addStudent(student, bitmap, Model.Storage.CLOUDINARY) {
                binding?.progressBar?.visibility = View.GONE
                Navigation.findNavController(view).popBackStack()
            }
        } else {
            Model.shared.addStudent(student, null, Model.Storage.CLOUDINARY) {
                binding?.progressBar?.visibility = View.VISIBLE
                Navigation.findNavController(view).popBackStack()
            }
        }
    }

    private fun onCancelClick(view: View) {
        Navigation.findNavController(view).popBackStack()
    }

}