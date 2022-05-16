package org.sopt.anshim.presentation.friend.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.anshim.data.models.db.FriendInfo
import org.sopt.anshim.data.models.types.MBTI
import org.sopt.anshim.data.models.types.MBTIFeatures
import org.sopt.anshim.domain.repositories.FriendRepository
import javax.inject.Inject

@HiltViewModel
class FriendDetailViewModel @Inject constructor(private val repository: FriendRepository) :
    ViewModel() {
    private val friend = MutableLiveData<FriendInfo?>()

    fun setFriend(friend: FriendInfo) {
        this.friend.value = friend
    }

    fun getFriend(): LiveData<FriendInfo?> = friend
    fun getMBTIFeature(mbti: MBTI): List<MBTIFeatures> = repository.getMBTIFeatures(mbti)

    companion object {
        private const val TAG = "FriendDetailViewModel"
    }
}