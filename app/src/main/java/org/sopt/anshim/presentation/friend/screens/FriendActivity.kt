package org.sopt.anshim.presentation.friend.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.anshim.R
import org.sopt.anshim.data.models.db.FriendInfo
import org.sopt.anshim.databinding.ActivityFriendBinding
import org.sopt.anshim.domain.models.UserInfo
import org.sopt.anshim.presentation.friend.adapters.FriendListAdapter
import org.sopt.anshim.presentation.friend.viewmodels.FriendViewModel
import org.sopt.anshim.presentation.github.GithubActivity

@AndroidEntryPoint
class FriendActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFriendBinding
    private val friendAdapter = FriendListAdapter(::onItemClick, ::onItemLongClick)
    private val viewModel: FriendViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_friend)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initView()
        addObservers()
    }

    private fun initView() {
        binding.friendList.adapter = friendAdapter
    }

    private fun addObservers() {
        viewModel.friends.observe(this) { friends ->
            friendAdapter.submitList(friends.toMutableList())
        }
    }

    private fun onItemClick(friend: FriendInfo) {
        val intent = Intent(this, GithubActivity::class.java).apply {
            putExtra(ARG_USER_INFO, UserInfo(friend.name, friend.mbti?.name, friend.email))
        }
        startActivity(intent)
    }

    private fun onItemLongClick(friend: FriendInfo): Boolean {
        viewModel.setSelectedFriendInfo(friend)
        return true
    }

    companion object {
        private const val TAG = "FriendActivity"
        private const val ARG_USER_INFO = "userInfo"
    }
}