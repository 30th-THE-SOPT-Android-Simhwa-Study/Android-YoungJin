package org.sopt.anshim.presentation.gallery

import android.net.Uri
import android.os.Build
import android.util.Size
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.anshim.data.models.GalleryData
import org.sopt.anshim.databinding.ItemGalleryImageBinding

class GalleryPagingAdapter(private val onItemClick: (Uri) -> Unit) :
    PagingDataAdapter<GalleryData, RecyclerView.ViewHolder>(diffCallback) {
    class ViewHolder(private val binding: ItemGalleryImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUri: GalleryData, onItemClick: (Uri) -> Unit) {
            with(binding) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val thumbnail = itemImage.context.contentResolver.loadThumbnail(
                        imageUri.uri,
                        Size(200, 200),
                        null
                    )
                    itemImage.load(thumbnail)
                } else {
                    itemImage.load(imageUri.uri)

                    // SDK 28 이하에서 bitmap 만드는 법
//                    MediaStore.Images.Thumbnails.getThumbnail(
//                        itemImage.context.contentResolver, imageUri.id,
//                        MediaStore.Images.Thumbnails.MINI_KIND,
//                        BitmapFactory.Options()
//                    )
                }

                container.setOnClickListener {
                    onItemClick(imageUri.uri)
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemGalleryImageBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val imageUri = getItem(position) ?: return
        when (viewHolder) {
            is ViewHolder -> viewHolder.bind(imageUri, onItemClick)
        }
    }

    companion object {
        private const val TAG = "GalleryPagingAdapter"

        private val diffCallback = object : DiffUtil.ItemCallback<GalleryData>() {
            override fun areItemsTheSame(
                oldItem: GalleryData,
                newItem: GalleryData,
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: GalleryData,
                newItem: GalleryData,
            ): Boolean = oldItem == newItem
        }
    }
}