package org.sopt.anshim.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.anshim.data.services.GithubService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {
    private const val GITHUB_BASE_URL = "https://api.github.com/"

    @Singleton
    @Provides
    fun bindGithubService(): GithubService =
        Retrofit.Builder().baseUrl(GITHUB_BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(GithubService::class.java)
}