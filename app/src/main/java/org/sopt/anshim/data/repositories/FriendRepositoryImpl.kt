package org.sopt.anshim.data.repositories

import androidx.lifecycle.LiveData
import org.sopt.anshim.data.datasources.FriendLocalDataSource
import org.sopt.anshim.data.models.db.FriendInfo
import org.sopt.anshim.data.models.types.Mbti
import org.sopt.anshim.data.models.types.MbtiFeatures
import org.sopt.anshim.domain.repositories.FriendRepository
import javax.inject.Inject

class FriendRepositoryImpl @Inject constructor(private val friendLocalDataSource: FriendLocalDataSource) :
    FriendRepository {
    override fun getAll(): LiveData<List<FriendInfo>> = friendLocalDataSource.getAll()

    override suspend fun insert(friend: FriendInfo) = friendLocalDataSource.insert(friend)

    override suspend fun update(friend: FriendInfo) = friendLocalDataSource.update(friend)

    override suspend fun delete(friend: FriendInfo) = friendLocalDataSource.delete(friend)

    override suspend fun deleteAll() = friendLocalDataSource.deleteAll()

    override fun getMBTIFeatures(mbti: Mbti): List<MbtiFeatures> =
        friendLocalDataSource.getMbtiFeatures(mbti)
}