package org.sopt.anshim.data.repositories

import org.sopt.anshim.data.datasources.GithubProfileRemoteDataSource
import org.sopt.anshim.domain.models.github.FollowerInfo
import org.sopt.anshim.domain.models.github.RepositoryInfo
import org.sopt.anshim.domain.repositories.GithubProfileRepository
import javax.inject.Inject

class GithubProfileRepositoryImpl @Inject constructor(
    private val remoteDataSource: GithubProfileRemoteDataSource,
) : GithubProfileRepository {
    override suspend fun fetchGithubFollowers(userName: String): List<FollowerInfo>? =
        remoteDataSource.fetchFollowers(userName)

    override suspend fun fetchGithubRepositories(userName: String): List<RepositoryInfo>? =
        remoteDataSource.fetchRepositories(userName)
}