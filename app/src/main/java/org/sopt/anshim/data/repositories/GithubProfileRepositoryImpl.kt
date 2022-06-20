package org.sopt.anshim.data.repositories

import kotlinx.coroutines.flow.Flow
import org.sopt.anshim.data.datasources.GithubProfileRemoteDataSource
import org.sopt.anshim.domain.models.UserInfo
import org.sopt.anshim.domain.models.github.FollowerInfo
import org.sopt.anshim.domain.models.github.RepositoryInfo
import org.sopt.anshim.domain.repositories.GithubProfileRepository
import javax.inject.Inject

class GithubProfileRepositoryImpl @Inject constructor(
    private val remoteDataSource: GithubProfileRemoteDataSource,
) : GithubProfileRepository {
    override suspend fun fetchUserInfo(userName: String): UserInfo? =
        remoteDataSource.fetchUserInfo(userName)

    override fun fetchGithubFollowers(userName: String): Flow<List<FollowerInfo>?> =
        remoteDataSource.fetchFollowers(userName)

    override fun fetchGithubRepositories(userName: String): Flow<List<RepositoryInfo>?> =
        remoteDataSource.fetchRepositories(userName)
}