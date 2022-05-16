package org.sopt.anshim.domain.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sopt.anshim.data.models.db.FriendDao
import org.sopt.anshim.data.models.db.FriendDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun bindFriendDatabase(@ApplicationContext context: Context): FriendDatabase {
        return Room.databaseBuilder(
            context,
            FriendDatabase::class.java,
            "friend_data_database"
        ).build()
    }

    @Provides
    fun bindFriendDao(friendDatabase: FriendDatabase): FriendDao {
        return friendDatabase.friendDao()
    }
}