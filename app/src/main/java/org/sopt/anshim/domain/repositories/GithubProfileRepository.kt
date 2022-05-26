package org.sopt.anshim.domain.repositories

import kotlinx.coroutines.flow.Flow
import org.sopt.anshim.domain.models.github.FollowerInfo
import org.sopt.anshim.domain.models.github.RepositoryInfo

interface GithubProfileRepository {
    fun fetchGithubFollowers(userName: String): Flow<List<FollowerInfo>?>
    fun fetchGithubRepositories(userName: String): Flow<List<RepositoryInfo>?>
}