package org.sopt.anshim.presentation.github

import PushEventListAdapter
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.sopt.anshim.R
import org.sopt.anshim.databinding.ActivityPushEventBinding

@AndroidEntryPoint
class PushEventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPushEventBinding
    private val viewModel: GithubPushEventViewModel by viewModels()
    private val pushEventListAdapter = PushEventListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_push_event)

        viewModel.setUserName(intent?.getStringExtra(ARG_USER_NAME) ?: return)

        initLayout()
        addObservers()
    }

    private fun initLayout() {
        binding.pushEventList.adapter = pushEventListAdapter
    }

    private fun addObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pushEvents.collect {
                    pushEventListAdapter.submitList(it.toMutableList())
                }
            }
        }

        binding.back.setOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        private const val TAG = "PushEventActivity"
        private const val ARG_USER_NAME = "userName"
    }
}