package org.sopt.anshim.presentation.friend.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import org.sopt.anshim.R
import org.sopt.anshim.data.datasources.FriendLocalDataSource
import org.sopt.anshim.databinding.ActivityFriendBinding
import org.sopt.anshim.data.models.db.FriendDatabase
import org.sopt.anshim.data.repositories.FriendRepositoryImpl
import org.sopt.anshim.data.models.db.FriendInfo
import org.sopt.anshim.presentation.friend.adapters.FriendListAdapter
import org.sopt.anshim.presentation.friend.viewmodels.FriendViewModel
import org.sopt.anshim.presentation.friend.viewmodels.FriendViewModelFactory

class FriendActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFriendBinding
    private val friendAdapter = FriendListAdapter(::onItemClick)
    private val viewModel: FriendViewModel by lazy {
        val dao = FriendDatabase.getInstance(application).friendDAO
        val repositoryImpl = FriendRepositoryImpl(FriendLocalDataSource(dao))
        repositoryImpl.friendLocalDataSource
        ViewModelProvider(this, FriendViewModelFactory(repositoryImpl))[FriendViewModel::class.java]
    }

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
        if (friend.mbti == null) return
        val intent = Intent(this, FriendDetailActivity::class.java).apply {
            putExtra(ARG_FRIEND_INFO, friend)
        }
        startActivity(intent)

        // TODO long click으로 변경
        // viewModel.setSelectedFriendInfo(friend)
    }

    companion object {
        private const val TAG = "FriendActivity"
        private const val ARG_FRIEND_INFO = "friendInfo"
    }
}