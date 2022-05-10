package org.sopt.anshim.domain.repositories

import androidx.lifecycle.LiveData
import org.sopt.anshim.data.models.db.FriendInfo
import org.sopt.anshim.data.models.types.MBTI
import org.sopt.anshim.data.models.types.MBTIFeatures

interface FriendRepository {
    fun getAll(): LiveData<List<FriendInfo>>

    suspend fun insert(friend: FriendInfo)

    suspend fun update(friend: FriendInfo)

    suspend fun delete(friend: FriendInfo)

    suspend fun deleteAll()

    fun getMBTIFeatures(mbti: MBTI): List<MBTIFeatures>
}