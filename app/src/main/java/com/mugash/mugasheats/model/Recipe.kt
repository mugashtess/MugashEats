package com.mugash.mugasheats.model

import android.R
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val ingredients: String,
    val steps: String,
    val time: String,
    val imagePath: String
)


