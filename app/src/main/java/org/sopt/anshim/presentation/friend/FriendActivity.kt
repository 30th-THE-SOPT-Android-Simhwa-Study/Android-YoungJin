package org.sopt.anshim.presentation.friend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import org.sopt.anshim.R
import org.sopt.anshim.databinding.ActivityFriendBinding
import org.sopt.anshim.data.models.db.FriendDatabase
import org.sopt.anshim.domain.FriendRepository
import org.sopt.anshim.domain.models.FriendInfo

class FriendActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFriendBinding
    private val friendAdapter = FriendListAdapter(::onItemClick)
    private val viewModel: FriendViewModel by lazy {
        val dao = FriendDatabase.getInstance(application).friendDAO
        val repository = FriendRepository(dao)
        ViewModelProvider(this, FriendViewModelFactory(repository))[FriendViewModel::class.java]
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
        viewModel.setSelectedFriendInfo(friend)
    }

    companion object {
        private const val TAG = "FriendActivity"
    }
}