package org.sopt.anshim.data.datasources

import androidx.lifecycle.LiveData
import org.sopt.anshim.data.models.db.FriendDao
import org.sopt.anshim.data.models.db.FriendInfo
import org.sopt.anshim.data.models.types.Mbti
import org.sopt.anshim.data.models.types.MbtiFeatures
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FriendLocalDataSource @Inject constructor(private val dao: FriendDao) {
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

    fun getMbtiFeatures(mbti: Mbti): List<MbtiFeatures> {
        return when (mbti) {
            Mbti.ISTJ -> listOf(MbtiFeatures.ISTJ1, MbtiFeatures.ISTJ2, MbtiFeatures.ISTJ3)
            Mbti.ISTP -> listOf(MbtiFeatures.ISTP1, MbtiFeatures.ISTP2, MbtiFeatures.ISTP3)
            Mbti.ISFJ -> listOf(MbtiFeatures.ISFJ1, MbtiFeatures.ISFJ2, MbtiFeatures.ISFJ3)
            Mbti.ISFP -> listOf(MbtiFeatures.ISFP1, MbtiFeatures.ISFP2, MbtiFeatures.ISFP3)

            Mbti.INTJ -> listOf(MbtiFeatures.INTJ1, MbtiFeatures.INTJ2, MbtiFeatures.INTJ3)
            Mbti.INTP -> listOf(MbtiFeatures.INTP1, MbtiFeatures.INTP2, MbtiFeatures.INTP3)
            Mbti.INFJ -> listOf(MbtiFeatures.INFJ1, MbtiFeatures.INFJ2, MbtiFeatures.INFJ3)
            Mbti.INFP -> listOf(MbtiFeatures.INFP1, MbtiFeatures.INFP2, MbtiFeatures.INFP3)

            Mbti.ESTJ -> listOf(MbtiFeatures.ESTJ1, MbtiFeatures.ESTJ2, MbtiFeatures.ESTJ3)
            Mbti.ESTP -> listOf(MbtiFeatures.ESTP1, MbtiFeatures.ESTP2, MbtiFeatures.ESTP3)
            Mbti.ESFJ -> listOf(MbtiFeatures.ESFJ1, MbtiFeatures.ESFJ2, MbtiFeatures.ESFJ3)
            Mbti.ESFP -> listOf(MbtiFeatures.ESFP1, MbtiFeatures.ESFP2, MbtiFeatures.ESFP3)

            Mbti.ENTJ -> listOf(MbtiFeatures.ENTJ1, MbtiFeatures.ENTJ2, MbtiFeatures.ENTJ3)
            Mbti.ENFJ -> listOf(MbtiFeatures.ENFJ1, MbtiFeatures.ENFJ2, MbtiFeatures.ENFJ3)
            Mbti.ENTP -> listOf(MbtiFeatures.ENTP1, MbtiFeatures.ENTP2, MbtiFeatures.ENTP3)
            Mbti.ENFP -> listOf(MbtiFeatures.ENFP1, MbtiFeatures.ENFP2, MbtiFeatures.ENFP3)
        }
    }
}