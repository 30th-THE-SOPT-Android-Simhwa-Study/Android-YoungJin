package org.sopt.anshim.domain.models.db

import androidx.lifecycle.LiveData
import androidx.room.*
import org.sopt.anshim.domain.models.FriendInfo

@Dao
interface FriendDAO {
    @Insert
    suspend fun insertFriend(friend: FriendInfo) : Long

    @Update
    suspend fun updateFriend(friend: FriendInfo)

    @Delete
    suspend fun deleteFriend(friend: FriendInfo)

    @Query("DELETE FROM friend_data_table")
    suspend fun deleteAllFriends()

    @Query("SELECT * FROM friend_data_table")
    fun getAllFriends(): LiveData<List<FriendInfo>>
}