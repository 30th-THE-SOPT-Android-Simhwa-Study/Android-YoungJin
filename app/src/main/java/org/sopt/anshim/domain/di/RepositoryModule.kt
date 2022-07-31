package org.sopt.anshim.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import org.sopt.anshim.data.repositories.FriendRepositoryImpl
import org.sopt.anshim.data.repositories.GalleryRepositoryImpl
import org.sopt.anshim.data.repositories.GithubProfileRepositoryImpl
import org.sopt.anshim.domain.repositories.FriendRepository
import org.sopt.anshim.domain.repositories.GalleryRepository
import org.sopt.anshim.domain.repositories.GithubProfileRepository

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindFriendRepository(friendRepositoryImpl: FriendRepositoryImpl): FriendRepository

    @Binds
    abstract fun bindGithubProfileRepository(githubProfileRepositoryImpl: GithubProfileRepositoryImpl): GithubProfileRepository

    @Binds
    abstract fun bindGalleryRepositoryImpl(galleryRepositoryImpl: GalleryRepositoryImpl): GalleryRepository
}