package com.example.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Donut(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val description: String = "",
    val rating: Int
)
