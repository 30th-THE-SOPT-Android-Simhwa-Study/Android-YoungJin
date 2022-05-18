package org.sopt.anshim.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import org.sopt.anshim.data.repositories.FriendRepositoryImpl
import org.sopt.anshim.domain.repositories.FriendRepository

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindFriendRepository(friendRepositoryImpl: FriendRepositoryImpl): FriendRepository
}