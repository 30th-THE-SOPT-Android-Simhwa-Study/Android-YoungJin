package org.sopt.anshim.presentation.types

import org.sopt.anshim.R

/** 팔로워, 팔로우, 레포지토리 목록을 보여주는 깃허브 detail viewType, 순서 바뀌지 않도록 주의 */
enum class GithubDetailViewType(val strRes: Int) {
    FOLLOWER(R.string.github_detail_follower),
    REPOSITORIES(R.string.github_detail_repository)
}