package org.sopt.anshim.data.datasources

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.sopt.anshim.data.services.GithubService
import org.sopt.anshim.domain.models.github.FollowerInfo
import org.sopt.anshim.domain.models.github.RepositoryInfo
import javax.inject.Inject

class GithubProfileRemoteDataSource @Inject constructor(private val githubService: GithubService) {
    fun fetchFollowers(userName: String): Flow<List<FollowerInfo>?> {
        return flow {
            while (true) {
                val followers = githubService.getFollowerList(userName).body()?.map { follower ->
                    follower.toFollowerInfo(follower)
                }
                emit(followers)
                delay(60000)
            }
        }
    }

    fun fetchRepositories(userName: String): Flow<List<RepositoryInfo>?> {
        return flow {
            while (true) {
                val repositories =
                    githubService.getRepositoryList(userName).body()?.map { repository ->
                        repository.toRepositoryInfo(repository)
                    }
                emit(repositories)
                delay(60000)
            }
        }
    }

    companion object {
        private const val TAG = "GithubProfileRemoteDataSource"
    }
}