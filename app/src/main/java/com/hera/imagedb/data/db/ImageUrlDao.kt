package com.hera.imagedb.data.db

import androidx.room.*
import com.hera.imagedb.data.module.ImageUrl
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageUrlDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addImageUrl(imageUrl: ImageUrl)

    @Delete
    suspend fun deleteImageUrl(imageUrl: ImageUrl)

    @Query("SELECT * FROM image_url")
    fun getAllImageUrls(): Flow<List<ImageUrl>>
}