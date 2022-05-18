package org.sopt.anshim.presentation.friend.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.anshim.R
import org.sopt.anshim.data.models.db.FriendInfo
import org.sopt.anshim.databinding.ActivityFriendDetailBinding
import org.sopt.anshim.presentation.friend.viewmodels.FriendDetailViewModel

@AndroidEntryPoint
class FriendDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFriendDetailBinding
    private val viewModel: FriendDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_friend_detail)
        binding.viewModel = viewModel

        intent.getParcelableExtra<FriendInfo>(ARG_FRIEND_INFO)?.let { friend ->
            viewModel.setFriend(friend)
        }
    }

    companion object {
        private const val ARG_FRIEND_INFO = "friendInfo"
    }
}