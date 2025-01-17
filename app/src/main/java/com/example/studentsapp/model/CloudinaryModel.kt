package com.example.studentsapp.model

import android.graphics.Bitmap
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.cloudinary.android.policy.GlobalUploadPolicy
import com.example.studentsapp.BuildConfig
import com.example.studentsapp.base.MyApplication
import com.example.studentsapp.base.UploadErrorCallback
import com.example.studentsapp.base.UploadSuccessCallback
import com.example.studentsapp.utils.extensions.toFile
import java.io.File

class CloudinaryModel {

    init {
        val config = mapOf(
            "cloud_name" to BuildConfig.CLOUD_NAME,
            "api_key" to BuildConfig.API_KEY,
            "api_secret" to BuildConfig.API_SECRET
        )

        MyApplication.Globals.context?.let {
            MediaManager.init(it, config)
            MediaManager.get().globalUploadPolicy = GlobalUploadPolicy.Builder()
                .maxConcurrentRequests(3)
                .build()
        }
    }

    fun uploadImage(
        bitmap: Bitmap,
        name: String,
        onSuccess: UploadSuccessCallback,
        onError: UploadErrorCallback
    ) {
        val context =
            MyApplication.Globals.context ?: return onError("No context found when uploading image")
        val file: File = bitmap.toFile(name, context)

        MediaManager.get().upload(file.path).option("folder", "images")
            .callback(object : UploadCallback {
                override fun onStart(requestId: String?) {
                }

                override fun onProgress(requestId: String?, bytes: Long, totalBytes: Long) {
                }

                override fun onSuccess(requestId: String?, resultData: MutableMap<*, *>) {
                    val url = resultData["secure_url"] as? String ?: ""
                    onSuccess(url)
                }

                override fun onError(requestId: String?, error: ErrorInfo?) {
                    onError(error?.description ?: "Unknown Error")
                }

                override fun onReschedule(requestId: String?, error: ErrorInfo?) {
                }

            })
            .dispatch()
    }
}