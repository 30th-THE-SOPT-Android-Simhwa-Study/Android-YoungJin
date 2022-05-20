package org.sopt.anshim.data.datasources

import org.sopt.anshim.data.services.GithubService
import org.sopt.anshim.domain.models.github.FollowerInfo
import org.sopt.anshim.domain.models.github.RepositoryInfo
import javax.inject.Inject

class GithubProfileRemoteDataSource @Inject constructor(private val githubService: GithubService) {
    suspend fun fetchFollowers(userName: String): List<FollowerInfo>? {
        runCatching {
            githubService.getFollowerList(userName)
        }.fold({
            return it.body()?.map { follower ->
                follower.toFollowerInfo(follower)
            }
        }, {
            it.printStackTrace()
            return null
        })
    }

    suspend fun fetchRepositories(userName: String): List<RepositoryInfo>? {
        runCatching {
            githubService.getRepositoryList(userName)
        }.fold({
            return it.body()?.map { repository ->
                repository.toRepositoryInfo(repository)
            }
        }, {
            it.printStackTrace()
            return null
        })
    }

    companion object {
        private const val TAG = "GithubProfileRemoteDataSource"
    }
}