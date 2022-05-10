package org.sopt.anshim.presentation.friend.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import org.sopt.anshim.R
import org.sopt.anshim.data.datasources.FriendLocalDataSource
import org.sopt.anshim.data.repositories.FriendRepositoryImpl
import org.sopt.anshim.data.models.db.FriendDatabase
import org.sopt.anshim.databinding.ActivityFriendDetailBinding
import org.sopt.anshim.data.models.db.FriendInfo
import org.sopt.anshim.presentation.friend.viewmodels.FriendDetailViewModel
import org.sopt.anshim.presentation.friend.viewmodels.FriendDetailViewModelFactory

class FriendDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFriendDetailBinding
    private val viewModel: FriendDetailViewModel by lazy {
        val dao = FriendDatabase.getInstance(application).friendDAO
        val repositoryImpl = FriendRepositoryImpl(FriendLocalDataSource(dao))
        ViewModelProvider(this, FriendDetailViewModelFactory(repositoryImpl))[FriendDetailViewModel::class.java]
    }

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