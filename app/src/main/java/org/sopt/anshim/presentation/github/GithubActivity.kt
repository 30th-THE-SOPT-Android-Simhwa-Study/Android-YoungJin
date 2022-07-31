package org.sopt.anshim.presentation.github

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import coil.load
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.anshim.R
import org.sopt.anshim.databinding.ActivityGithubBinding
import org.sopt.anshim.domain.models.UserInfo
import org.sopt.anshim.presentation.gallery.GalleryActivity
import org.sopt.anshim.presentation.github.adapters.GithubAdapter
import org.sopt.anshim.presentation.types.GithubDetailViewType

@AndroidEntryPoint
class GithubActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGithubBinding
    private val viewModel: GithubViewModel by viewModels()
    private val tabTitles = arrayOf(
        GithubDetailViewType.FOLLOWER.strRes,
        GithubDetailViewType.REPOSITORIES.strRes
    )
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_github)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        (intent?.getParcelableExtra(ARG_USER_INFO) as? UserInfo)?.let { user ->
            viewModel.setUserName(user.name)
        }

        setSignUpResult()
        initLayout()
    }

    private fun initLayout() {
        binding.githubDetail.run {
            adapter = GithubAdapter(this@GithubActivity)
            setCurrentItem(GithubDetailViewType.FOLLOWER.ordinal, false)
        }

        binding.profileImage.setOnClickListener {
            requestStorage.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        TabLayoutMediator(binding.tab, binding.githubDetail) { tab, position ->
            tab.text = getString(tabTitles[position])
        }.attach()
    }

    private fun setSignUpResult() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode != Activity.RESULT_OK) return@registerForActivityResult
                val data = result.data ?: return@registerForActivityResult
                binding.profileImage.load(data.getParcelableExtra(ARG_IMAGE_URI))
            }
    }

    private val requestStorage =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) resultLauncher.launch(Intent(this, GalleryActivity::class.java))
        }

    companion object {
        private const val TAG = "GithubFragment"
        private const val ARG_USER_INFO = "userInfo"
        private const val ARG_IMAGE_URI = "imageUri"
    }
}