package com.hera.imagedb.data.repository

import com.hera.imagedb.data.db.ImageUrlDao
import com.hera.imagedb.data.module.ImageUrl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface ImageUrlRepository {

    suspend fun addImageUrl(url: String)

    suspend fun deleteImageUrl(imageUrl: ImageUrl)

    fun getAllImageUrls(): Flow<List<ImageUrl>>
}

class ImageUrlRepositoryImpl @Inject constructor(
    private val imgUrlDao: ImageUrlDao
) : ImageUrlRepository {

    override suspend fun addImageUrl(url: String) {
        imgUrlDao.addImageUrl(ImageUrl(0, url))
    }

    override suspend fun deleteImageUrl(imagerUrl: ImageUrl) {
        imgUrlDao.deleteImageUrl(imagerUrl)
    }

    override fun getAllImageUrls(): Flow<List<ImageUrl>> {
        return imgUrlDao.getAllImageUrls()
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface ImageUrlRepositoryModule {

    @Binds
    @Singleton
    fun bindImageUrlRepository(impl: ImageUrlRepositoryImpl): ImageUrlRepository
}