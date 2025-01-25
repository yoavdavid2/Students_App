package com.example.studentsapp.model

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.studentsapp.base.MyApplication
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue

@Entity
data class Student(
    @PrimaryKey val id: String,
    val name: String,
    val avatarUrl: String,
    var isChecked: Boolean,
    var lastUpdated: Long? = null
) {

    companion object {

        var lastUpdated: Long
            get() = MyApplication.Globals.context?.getSharedPreferences("TAG", Context.MODE_PRIVATE)
                ?.getLong(LOCAL_LAST_UPDATED_KEY, 0) ?: 0
            set(value) {
                MyApplication.Globals.context
                    ?.getSharedPreferences("TAG", Context.MODE_PRIVATE)
                    ?.edit()
                    ?.putLong(LOCAL_LAST_UPDATED_KEY, value)
                    ?.apply()
            }

        const val ID_KEY = "id"
        const val NAME_KEY = "name"
        const val AVATAR_URL_KEY = "avatarUrl"
        const val IS_CHECKED_KEY = "isChecked"
        const val LAST_UPDATED_KEY = "lastUpdated"
        const val LOCAL_LAST_UPDATED_KEY = "localStudentLastUpdated"

        fun fromJson(json: Map<String, Any>): Student {
            val id = json["id"] as? String ?: ""
            val name = json["name"] as? String ?: ""
            val avatarUrl = json["avatarUrl"] as? String ?: ""
            val isChecked = json["isChecked"] as? Boolean ?: false
            val timeStamp = json[LAST_UPDATED_KEY] as? Timestamp

            val lastUpdatedLongTimestamp = timeStamp?.toDate()?.time

            return Student(
                id = id,
                name = name,
                avatarUrl = avatarUrl,
                isChecked = isChecked,
                lastUpdated = lastUpdatedLongTimestamp
            )
        }
    }

    val json: Map<String, Any>
        get() = hashMapOf(
            ID_KEY to id,
            NAME_KEY to name,
            AVATAR_URL_KEY to avatarUrl,
            IS_CHECKED_KEY to isChecked,
            LAST_UPDATED_KEY to FieldValue.serverTimestamp()
        )
}
