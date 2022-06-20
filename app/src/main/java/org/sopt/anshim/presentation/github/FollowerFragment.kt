package org.sopt.anshim.presentation.github

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.sopt.anshim.databinding.FragmentFollowerBinding
import org.sopt.anshim.presentation.github.adapters.FollowerListAdapter

@AndroidEntryPoint
class FollowerFragment : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    val binding get() = _binding!!
    private val viewModel: GithubViewModel by activityViewModels()
    private val followerListAdapter = FollowerListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
        addObservers()
    }

    private fun initLayout() {
        binding.followerList.adapter = followerListAdapter
    }

    private fun addObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.followers.collect {
                    followerListAdapter.submitList(it?.toMutableList())
                }
            }
        }
    }

    companion object {
        private const val TAG = "FollowerFragment"
    }
}