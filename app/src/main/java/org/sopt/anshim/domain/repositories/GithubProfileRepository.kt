package org.sopt.anshim.domain.repositories

import org.sopt.anshim.domain.models.github.FollowerInfo
import org.sopt.anshim.domain.models.github.RepositoryInfo

interface GithubProfileRepository {
    suspend fun fetchGithubFollowers(userName: String): List<FollowerInfo>?
    suspend fun fetchGithubRepositories(userName: String): List<RepositoryInfo>?
}