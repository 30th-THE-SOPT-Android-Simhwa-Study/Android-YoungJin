package org.sopt.anshim.data.services

import org.sopt.anshim.data.models.github.ResponseFollower
import org.sopt.anshim.data.models.github.ResponsePushEvent
import org.sopt.anshim.data.models.github.ResponseRepository
import org.sopt.anshim.data.models.github.ResponseUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{user_name}/followers")
    suspend fun getFollowerList(@Path("user_name") userName: String): Response<List<ResponseFollower>>

    @GET("users/{user_name}/repos")
    suspend fun getRepositoryList(@Path("user_name") userName: String): Response<List<ResponseRepository>>

    @GET("users/{username}")
    suspend fun getUserInfo(@Path("username") userName: String): Response<ResponseUser>

    @GET("users/{username}/received_events")
    suspend fun getEventInfo(@Path("username") userName: String): Response<List<ResponsePushEvent>>
}