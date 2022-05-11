package org.sopt.anshim.data.datasources

import androidx.lifecycle.LiveData
import org.sopt.anshim.data.models.db.FriendDAO
import org.sopt.anshim.data.models.db.FriendInfo
import org.sopt.anshim.data.models.types.MBTI
import org.sopt.anshim.data.models.types.MBTIFeatures
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FriendLocalDataSource @Inject constructor(private val dao: FriendDAO) {
    private val friends = dao.getAllFriends()

    fun getAll(): LiveData<List<FriendInfo>> {
        return friends
    }

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

    fun getMBTIFeatures(mbti: MBTI): List<MBTIFeatures> {
        return when (mbti) {
            MBTI.ISTJ -> listOf(MBTIFeatures.ISTJ1, MBTIFeatures.ISTJ2, MBTIFeatures.ISTJ3)
            MBTI.ISTP -> listOf(MBTIFeatures.ISTP1, MBTIFeatures.ISTP2, MBTIFeatures.ISTP3)
            MBTI.ISFJ -> listOf(MBTIFeatures.ISFJ1, MBTIFeatures.ISFJ2, MBTIFeatures.ISFJ3)
            MBTI.ISFP -> listOf(MBTIFeatures.ISFP1, MBTIFeatures.ISFP2, MBTIFeatures.ISFP3)

            MBTI.INTJ -> listOf(MBTIFeatures.INTJ1, MBTIFeatures.INTJ2, MBTIFeatures.INTJ3)
            MBTI.INTP -> listOf(MBTIFeatures.INTP1, MBTIFeatures.INTP2, MBTIFeatures.INTP3)
            MBTI.INFJ -> listOf(MBTIFeatures.INFJ1, MBTIFeatures.INFJ2, MBTIFeatures.INFJ3)
            MBTI.INFP -> listOf(MBTIFeatures.INFP1, MBTIFeatures.INFP2, MBTIFeatures.INFP3)

            MBTI.ESTJ -> listOf(MBTIFeatures.ESTJ1, MBTIFeatures.ESTJ2, MBTIFeatures.ESTJ3)
            MBTI.ESTP -> listOf(MBTIFeatures.ESTP1, MBTIFeatures.ESTP2, MBTIFeatures.ESTP3)
            MBTI.ESFJ -> listOf(MBTIFeatures.ESFJ1, MBTIFeatures.ESFJ2, MBTIFeatures.ESFJ3)
            MBTI.ESFP -> listOf(MBTIFeatures.ESFP1, MBTIFeatures.ESFP2, MBTIFeatures.ESFP3)

            MBTI.ENTJ -> listOf(MBTIFeatures.ENTJ1, MBTIFeatures.ENTJ2, MBTIFeatures.ENTJ3)
            MBTI.ENFJ -> listOf(MBTIFeatures.ENFJ1, MBTIFeatures.ENFJ2, MBTIFeatures.ENFJ3)
            MBTI.ENTP -> listOf(MBTIFeatures.ENTP1, MBTIFeatures.ENTP2, MBTIFeatures.ENTP3)
            MBTI.ENFP -> listOf(MBTIFeatures.ENFP1, MBTIFeatures.ENFP2, MBTIFeatures.ENFP3)
        }
    }
}