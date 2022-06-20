package org.sopt.anshim.util

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
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
    Glide.with(this.context).load(url).error(R.drawable.ic_welcome).into(this)
}