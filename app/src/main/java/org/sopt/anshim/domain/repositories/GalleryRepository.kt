package org.sopt.anshim.domain.repositories

import org.sopt.anshim.data.datasources.GalleryPagingDataSource

interface GalleryRepository {
    fun galleryPagingSource(): GalleryPagingDataSource
}