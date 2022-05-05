package org.sopt.anshim.domain

import org.sopt.anshim.domain.models.db.FriendDAO
import org.sopt.anshim.domain.models.FriendInfo

class FriendRepository(private val dao: FriendDAO) {
    val friends = dao.getAllFriends()

    suspend fun insert(friend: FriendInfo) {
        dao.insertFriend(friend)
    }

    suspend fun update(friend: FriendInfo) {
        dao.updateFriend(friend)
    }

    suspend fun delete(friend: FriendInfo) {
        dao.deleteFriend(friend)
    }

    suspend fun deleteAll() {
        dao.deleteAllFriends()
    }
}