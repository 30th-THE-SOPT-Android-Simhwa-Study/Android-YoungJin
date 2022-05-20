package org.sopt.anshim.presentation.github

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.sopt.anshim.domain.models.github.FollowerInfo
import org.sopt.anshim.domain.models.github.RepositoryInfo
import org.sopt.anshim.domain.repositories.GithubProfileRepository
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val githubProfileRepo: GithubProfileRepository,
) : ViewModel() {
    private var followers = MutableLiveData<List<FollowerInfo>?>()
    private var repositories = MutableLiveData<MutableList<RepositoryInfo>>()

    init {
        fetchGithubList()
    }

    private fun fetchGithubList() {
        viewModelScope.launch(Dispatchers.IO) {
            // TODO UserInfo에 github 전용 username 추가 후, userInfo.githubUserName 으로 접근
            followers.postValue(githubProfileRepo.fetchGithubFollowers("youngjinc"))
            repositories.postValue(githubProfileRepo.fetchGithubRepositories("youngjinc")
                ?.toMutableList())
        }
    }

    fun getFollower(): LiveData<List<FollowerInfo>?> = followers
    fun getRepositories(): LiveData<MutableList<RepositoryInfo>> = repositories
}