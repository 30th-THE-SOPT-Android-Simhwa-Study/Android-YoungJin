package org.sopt.anshim.presentation.github

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.anshim.R
import org.sopt.anshim.databinding.ActivityGithubBinding
import org.sopt.anshim.domain.models.UserInfo
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_github)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        (intent?.getParcelableExtra(ARG_USER_INFO) as? UserInfo)?.let { user ->
            viewModel.setUserName(user.name)
        }

        initLayout()
    }

    private fun initLayout() {
        binding.githubDetail.run {
            adapter = GithubAdapter(this@GithubActivity)
            setCurrentItem(GithubDetailViewType.FOLLOWER.ordinal, false)
        }

        TabLayoutMediator(binding.tab, binding.githubDetail) { tab, position ->
            tab.text = getString(tabTitles[position])
        }.attach()
    }

    companion object {
        private const val TAG = "GithubFragment"
        private const val ARG_USER_INFO = "userInfo"
    }
}