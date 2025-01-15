package com.example.studentsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey val id: String,
    val name: String,
    val avatarUrl: String,
    var isChecked: Boolean,
) {

    companion object {

        const val ID_KEY = "id"
        const val NAME_KEY = "name"
        const val AVATAR_URL_KEY = "avatarUrl"
        const val IS_CHECKED_KEY = "isChecked"

        fun fromJson(json: Map<String, Any>): Student {
            val id = json["id"] as? String ?: ""
            val name = json["name"] as? String ?: ""
            val avatarUrl = json["avatarUrl"] as? String ?: ""
            val isChecked = json["isChecked"] as? Boolean ?: false

            return Student(
                id = id,
                name = name,
                avatarUrl = avatarUrl,
                isChecked = isChecked
            )
        }
    }

    val json: Map<String, Any>
        get() = hashMapOf(
            ID_KEY to id,
            NAME_KEY to name,
            AVATAR_URL_KEY to avatarUrl,
            IS_CHECKED_KEY to isChecked,
        )
}
