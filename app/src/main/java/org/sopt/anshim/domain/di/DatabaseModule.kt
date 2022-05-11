package org.sopt.anshim.domain.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sopt.anshim.data.models.db.FriendDAO
import org.sopt.anshim.data.models.db.FriendDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun bindFriendDatabase(@ApplicationContext context: Context): FriendDatabase {
        return FriendDatabase.getInstance(context)
    }

    @Provides
    fun bindFriendDao(friendDatabase: FriendDatabase): FriendDAO {
        return friendDatabase.friendDAO()
    }
}