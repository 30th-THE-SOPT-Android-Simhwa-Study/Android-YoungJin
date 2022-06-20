package org.sopt.anshim.domain.models.github

import org.sopt.anshim.data.models.github.CommitInfo
import org.sopt.anshim.data.models.github.OrgInfo

/** ResponsePushEvent의 commit list 중 하나의 commit 정보만을 담는 data class */
data class PushEventSingleCommitInfo(
    val id: Double,
    val org: OrgInfo,
    val commit: CommitInfo,
)