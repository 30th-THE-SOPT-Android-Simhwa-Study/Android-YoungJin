package org.sopt.anshim.presentation.gallery

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.sopt.anshim.R
import org.sopt.anshim.databinding.ActivityGalleryBinding
import org.sopt.anshim.presentation.github.GithubActivity

@AndroidEntryPoint
class GalleryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGalleryBinding
    private lateinit var adapter: GalleryPagingAdapter
    private val viewModel: GalleryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gallery)

        initializeView()
        addCollectors()
    }

    private fun initializeView() {
        adapter = GalleryPagingAdapter(::onItemClick)
        binding.imageList.adapter = adapter
        binding.back.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun onItemClick(imageUri: Uri) {
        moveToPrevious(imageUri)
    }

    private fun addCollectors() {
        lifecycleScope.launch {
            viewModel.imageList.collectLatest { image ->
                adapter.submitData(image)
            }
        }
    }

    private fun moveToPrevious(galleryImageUri: Uri) {
        val intent = Intent(this, GithubActivity::class.java)
        intent.putExtra(ARG_IMAGE_URI, galleryImageUri)
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        private const val TAG = "GalleryImageFragment"
        private const val ARG_IMAGE_URI = "imageUri"
    }
}