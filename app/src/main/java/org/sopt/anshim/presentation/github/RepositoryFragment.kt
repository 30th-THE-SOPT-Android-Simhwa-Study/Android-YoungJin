package org.sopt.anshim.presentation.github

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.anshim.databinding.FragmentFollowerBinding
import org.sopt.anshim.databinding.FragmentRepositoryBinding
import org.sopt.anshim.presentation.github.adapters.RepositoryListAdapter

@AndroidEntryPoint
class RepositoryFragment : Fragment() {
    private var _binding: FragmentRepositoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GithubViewModel by viewModels()
    private var adapter = RepositoryListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRepositoryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
        addListeners()
    }

    private fun initLayout() {
        binding.repositoryList.apply {
            adapter = this@RepositoryFragment.adapter
            addItemDecoration(DividerItemDecoration(this.context,
                LinearLayoutManager(requireContext()).orientation))
        }
    }

    private fun addListeners() {
        viewModel.getRepositories().observe(viewLifecycleOwner) {
            Log.i(TAG, "addListeners: $it")
            adapter.submitList(it.toMutableList())
        }
    }

    companion object {
        private const val TAG = "RepositoryFragment"
    }
}