package com.example.studentsapp.utils.extensions

import android.content.Context
import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream


fun Bitmap.toFile(name: String, context: Context): File {
    val file = File(context.cacheDir, "$name.jpg")
    FileOutputStream(file).use { outputStream ->
        compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    }
    return file
}