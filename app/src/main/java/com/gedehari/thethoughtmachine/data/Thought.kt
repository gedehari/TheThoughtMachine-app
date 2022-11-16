package com.gedehari.thethoughtmachine.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "thoughts")
data class Thought (
    @PrimaryKey
    val id: Int,

    @ColumnInfo
    val title: String,

    @ColumnInfo
    val author: String,

    @ColumnInfo
    val message: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Long
)
