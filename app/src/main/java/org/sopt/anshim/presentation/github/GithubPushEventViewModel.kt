package org.sopt.anshim.presentation.github

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.sopt.anshim.data.models.github.CommitInfo
import org.sopt.anshim.data.models.github.ResponsePushEvent
import org.sopt.anshim.data.services.GithubService
import org.sopt.anshim.domain.models.UserInfo
import org.sopt.anshim.domain.models.github.PushEventSingleCommitInfo
import javax.inject.Inject

// TODO GithuvViewModel과 합치는 거 고려해보기
//  현재 합치지 못한 이유 : PushEvent를 보여주는 화면에서는 GithuvViewModel의 follower나 repository처럼 데이터 변경사항을 구독할 필요가 없기 때문.
//  솔루션 : 각종 깃허브 정보(follower, repository, pushEvents)는 Repository에서 flow 타입으로 반환하고, viewModel에서 collect하는 함수 생성
//  -> 각 뷰에서 불러오고 싶은 정보에 대응되는 fetch 함수를 호출

@HiltViewModel
class GithubPushEventViewModel @Inject constructor(
    private val githubService: GithubService,
) : ViewModel() {
    private var userName: String? = null
    val pushEvents = flow {
        while (true) {
            val pushInfoList = mutableListOf<PushEventSingleCommitInfo>()
            githubService.getEventInfo(userName ?: return@flow).body()?.forEach { event ->
                if (event.type != "PushEvent") return@forEach

                pushInfoList.addAll(getRefinedPushEventList(event))
                emit(pushInfoList)
                delay(20000)
            }
        }
    }

    /** 서버에서 받은 ResponsePushEvent의 commit list를 낱개의 commit 정보만을 담는 PushEventSingleCommitInfo data class로 변환 후 반환 */
    private suspend fun getRefinedPushEventList(pushEvent: ResponsePushEvent): List<PushEventSingleCommitInfo> {
        val pushInfoList = mutableListOf<PushEventSingleCommitInfo>()
        pushEvent.payload.commits.forEach() { commit ->
            /* ResponsePushEvent로 받아온 commit의 author 정보는 profile 이미지 값이 존재하지 않음
            따라서 author의 username으로 깃허브 userInfo 정보를 조회해서 author의 profile 이미지 값을 포함한 깃허브 user정보를 가져온다.*/
            val author = getAuthorInfo(commit.author.name) ?: return@forEach
            pushInfoList.add(pushEvent.toPushEventCommitInfo(pushEvent, CommitInfo(author, commit.message)))
        }
        return pushInfoList
    }

    private suspend fun getAuthorInfo(userName: String): UserInfo? {
        return withContext(viewModelScope.coroutineContext) {
            val user = githubService.getUserInfo(userName).body() ?: return@withContext null
            UserInfo(name = user.name, profile = user.profile)
        }
    }

    fun setUserName(name: String) {
        userName = name
    }

    companion object {
        private const val TAG = "GithubPushViewModel"
    }
}