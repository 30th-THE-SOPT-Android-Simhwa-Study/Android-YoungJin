package org.sopt.anshim.presentation.thread

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.anshim.presentation.types.ProgressState

class ThreadViewModel : ViewModel() {
    private var _image = MutableLiveData<Bitmap>()
    val image: LiveData<Bitmap> get() = _image
    private var _count = MutableLiveData<Int>()
    val count: LiveData<Int> get() = _count
    private val _isLoading = MutableLiveData(ProgressState.IDLE)
    val isLoading: LiveData<ProgressState> get() = _isLoading

    fun setImage(image: Bitmap) {
        _image.value = image
    }

    fun setCount(count: Int) {
        _count.value = count
    }

    fun setLoadingState(state: ProgressState) {
        _isLoading.postValue(state)
    }
}