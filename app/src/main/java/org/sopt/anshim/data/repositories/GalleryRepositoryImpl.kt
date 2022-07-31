package org.sopt.anshim.data.repositories

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import org.sopt.anshim.data.datasources.GalleryPagingDataSource
import org.sopt.anshim.domain.repositories.GalleryRepository
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : GalleryRepository {
    override fun galleryPagingSource() = GalleryPagingDataSource(context)
}