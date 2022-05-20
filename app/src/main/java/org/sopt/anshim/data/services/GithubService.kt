package org.sopt.anshim.data.services

import org.sopt.anshim.data.models.github.ResponseFollower
import org.sopt.anshim.data.models.github.ResponseRepository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{user_name}/followers")
    suspend fun getFollowerList(@Path("user_name") userName: String): Response<List<ResponseFollower>>

    @GET("users/{user_name}/repos")
    suspend fun getRepositoryList(@Path("user_name") userName: String): Response<List<ResponseRepository>>
}