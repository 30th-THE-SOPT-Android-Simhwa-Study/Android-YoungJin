package org.sopt.anshim.util

import android.graphics.Bitmap
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.load
import com.google.android.material.imageview.ShapeableImageView
import org.sopt.anshim.R

@BindingAdapter("android:visibility")
fun View.setVisibility(isVisible: Boolean) {
    this.isVisible = isVisible
}

@BindingAdapter("android:invisibility")
fun View.setInvisibility(isInvisible: Boolean) {
    this.isInvisible = isInvisible
}

@BindingAdapter("android:shapeableImageUrl")
fun ShapeableImageView.setImageURl(url: String?) {
    load(url) { placeholder(R.drawable.ic_welcome) }
}

@BindingAdapter("app:shapeableBitmap")
fun ShapeableImageView.setBitmap(bitmap: Bitmap?) {
    if (bitmap == null) return
    load(bitmap)
}