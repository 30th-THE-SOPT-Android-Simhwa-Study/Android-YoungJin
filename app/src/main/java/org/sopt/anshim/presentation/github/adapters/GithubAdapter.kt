package org.sopt.anshim.presentation.github.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.sopt.anshim.presentation.github.FollowerFragment
import org.sopt.anshim.presentation.github.RepositoryFragment
import org.sopt.anshim.presentation.types.GithubDetailViewType

class GithubAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int = GithubDetailViewType.values().size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FollowerFragment()
            else -> RepositoryFragment()
        }
    }
}