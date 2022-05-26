package org.sopt.anshim.presentation.github

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import org.sopt.anshim.domain.models.github.FollowerInfo
import org.sopt.anshim.domain.models.github.RepositoryInfo
import org.sopt.anshim.domain.repositories.GithubProfileRepository
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val githubProfileRepo: GithubProfileRepository,
) : ViewModel() {
    private var _userName = "youngjinc"
    private val userName get() = _userName

    val followers: Flow<List<FollowerInfo>?> = githubProfileRepo.fetchGithubFollowers(userName)
    val repositories: Flow<List<RepositoryInfo>?> =
        githubProfileRepo.fetchGithubRepositories(userName)

    companion object {
        private const val TAG = "GithubViewModel"
    }
}