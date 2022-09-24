package com.hera.imagedb.data.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImageUrlDatabaseModule {

    @Provides
    @Singleton
    fun provideImageUrlDao(@ApplicationContext context: Context): ImageUrlDao {
        val db = Room
            .databaseBuilder(context, ImageUrlDatabase::class.java, "ImageUrl.db")
            .createFromAsset("database/image_url.db")
            .build()
        return db.getDao()
    }
}