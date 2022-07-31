package org.sopt.anshim.presentation.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import org.sopt.anshim.data.models.GalleryData
import org.sopt.anshim.domain.repositories.GalleryRepository
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val galleryRepository: GalleryRepository,
) : ViewModel() {
    val imageList: Flow<PagingData<GalleryData>> =
        Pager(PagingConfig(pageSize = 30)) {
            galleryRepository.galleryPagingSource()
        }.flow.cachedIn(viewModelScope)

    companion object {
        private const val TAG = "GalleryViewModel"
    }
}