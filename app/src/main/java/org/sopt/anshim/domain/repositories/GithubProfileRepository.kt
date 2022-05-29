package org.sopt.anshim.domain.repositories

import kotlinx.coroutines.flow.Flow
import org.sopt.anshim.domain.models.UserInfo
import org.sopt.anshim.domain.models.github.FollowerInfo
import org.sopt.anshim.domain.models.github.RepositoryInfo

interface GithubProfileRepository {
    suspend fun fetchUserInfo(userName: String): UserInfo?
    fun fetchGithubFollowers(userName: String): Flow<List<FollowerInfo>?>
    fun fetchGithubRepositories(userName: String): Flow<List<RepositoryInfo>?>
}