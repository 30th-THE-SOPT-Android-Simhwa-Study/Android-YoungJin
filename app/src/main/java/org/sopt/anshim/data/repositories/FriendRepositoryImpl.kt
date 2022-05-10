package org.sopt.anshim.data.repositories

import androidx.lifecycle.LiveData
import org.sopt.anshim.data.datasources.FriendLocalDataSource
import org.sopt.anshim.domain.repositories.FriendRepository
import org.sopt.anshim.data.models.db.FriendInfo
import org.sopt.anshim.data.models.types.MBTI
import org.sopt.anshim.data.models.types.MBTIFeatures

class FriendRepositoryImpl(val friendLocalDataSource: FriendLocalDataSource):
    FriendRepository {
    override fun getAll(): LiveData<List<FriendInfo>> {
        return friendLocalDataSource.getAll()
    }

    override suspend fun insert(friend: FriendInfo) {
        friendLocalDataSource.insert(friend)
    }

    override suspend fun update(friend: FriendInfo) {
        friendLocalDataSource.update(friend)
    }

    override suspend fun delete(friend: FriendInfo) {
        friendLocalDataSource.delete(friend)
    }

    override suspend fun deleteAll() {
        friendLocalDataSource.deleteAll()
    }

    override fun getMBTIFeatures(mbti: MBTI): List<MBTIFeatures> {
        return friendLocalDataSource.getMBTIFeatures(mbti)
    }
}