package org.sopt.anshim.data.models.github

import org.sopt.anshim.domain.models.github.PushEventSingleCommitInfo

data class ResponsePushEvent(
    val id: Double,
    val type: String,
    val org: OrgInfo,
    val payload: PayloadInfo,
) {
    data class PayloadInfo(
        val commits: List<CommitInfo>,
    )

    fun toPushEventCommitInfo(pushEvent: ResponsePushEvent, commitInfo: CommitInfo): PushEventSingleCommitInfo =
        PushEventSingleCommitInfo(pushEvent.id, pushEvent.org, commitInfo)
}