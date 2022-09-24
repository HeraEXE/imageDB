package com.hera.imagedb.data.module

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_url")
data class ImageUrl(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val url: String
)