package com.muhammed.ontime.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    var title: String? = null,
    var text: String,
    var initDate: Long = System.currentTimeMillis(),
    var isPinned: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
