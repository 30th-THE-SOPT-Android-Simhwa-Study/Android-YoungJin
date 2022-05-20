package org.sopt.anshim.presentation.github.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.anshim.databinding.ItemRepositoryBinding
import org.sopt.anshim.domain.models.github.RepositoryInfo

class RepositoryListAdapter : ListAdapter<RepositoryInfo, RecyclerView.ViewHolder>(diffCallback) {
    class FollowerViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: RepositoryInfo) {
            with(binding) {
                this.repository = repository
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemRepositoryBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val data = currentList[position]
        when (viewHolder) {
            is FollowerViewHolder -> viewHolder.bind(data)
        }
    }

    companion object {
        private const val TAG = "RepositoryListAdapter"

        private val diffCallback = object : DiffUtil.ItemCallback<RepositoryInfo>() {
            override fun areItemsTheSame(
                oldItem: RepositoryInfo,
                newItem: RepositoryInfo,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: RepositoryInfo,
                newItem: RepositoryInfo,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}