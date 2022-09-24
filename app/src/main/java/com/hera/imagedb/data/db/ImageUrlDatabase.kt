package com.hera.imagedb.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hera.imagedb.data.module.ImageUrl

@Database(entities = [ImageUrl::class], version = 1, exportSchema = false)
abstract class ImageUrlDatabase : RoomDatabase() {

    abstract fun getDao(): ImageUrlDao
}