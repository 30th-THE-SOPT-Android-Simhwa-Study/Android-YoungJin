package org.sopt.anshim.data.models.github

import org.sopt.anshim.domain.models.UserInfo

data class CommitInfo(
    val author: UserInfo,
    val message: String,
)