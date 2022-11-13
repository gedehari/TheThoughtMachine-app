package com.gedehari.thethoughtmachine.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "thoughts")
data class Thought (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo
    val title: String,

    @ColumnInfo
    val author: String?,

    @ColumnInfo
    val message: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Date? = null
)
