package org.sopt.anshim.data.models.db

import androidx.lifecycle.LiveData
import androidx.room.*

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

    @Query("SELECT * FROM friend_data_table ORDER BY id DESC")
    fun getAllFriends(): LiveData<List<FriendInfo>>
}