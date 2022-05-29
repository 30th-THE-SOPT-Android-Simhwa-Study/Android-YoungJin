package org.sopt.anshim.presentation.github

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import org.sopt.anshim.data.services.GithubService
import org.sopt.anshim.domain.models.UserInfo
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val githubService: GithubService,
) : ViewModel() {
    private var userName: String? = null
    val userInfo = flow {
        while (true) {
            val user = githubService.getUserInfo(userName ?: return@flow).body()?.run {
                toUserInfo(this)
            }
            emit(user)
            delay(20000)
        }
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5000),
        initialValue = UserInfo("Loading...", null, "")
    )
    val followers = flow {
        while (true) {
            val followers =
                githubService.getFollowerList(userName ?: return@flow).body()?.map { follower ->
                    follower.toFollowerInfo(follower)
                }
            emit(followers)
            delay(20000)
        }
    }
    val repositories = flow {
        while (true) {
            val repositories =
                githubService.getRepositoryList(userName ?: return@flow).body()?.map { repository ->
                    repository.toRepositoryInfo(repository)
                }
            emit(repositories)
            delay(20000)
        }
    }

    fun setUserName(name: String) {
        userName = name
    }

    companion object {
        private const val TAG = "GithubViewModel"
    }
}