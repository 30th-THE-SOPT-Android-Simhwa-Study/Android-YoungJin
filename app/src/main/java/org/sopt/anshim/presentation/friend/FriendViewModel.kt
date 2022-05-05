package org.sopt.anshim.presentation.friend

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.anshim.domain.models.FriendInfo
import org.sopt.anshim.domain.FriendRepository
import org.sopt.anshim.util.safeLet

class FriendViewModel(private val repository: FriendRepository) : ViewModel() {

    val friends = repository.friends
    val inputName = MutableLiveData<String?>()
    val inputEmail = MutableLiveData<String?>()

    fun saveFriend() {
        viewModelScope.launch {
            safeLet(inputName.value, inputEmail.value) { name, email ->
                repository.insert(FriendInfo(0, name, email))
                clearFriendInput()
            }
        }
    }

    fun updateFriend(Friend: FriendInfo) {
        viewModelScope.launch {
            repository.update(Friend)
        }
    }

    fun deleteFriend(Friend: FriendInfo) {
        viewModelScope.launch {
            repository.delete(Friend)
        }
    }

    fun deleteAllFriends() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    private fun clearFriendInput() {
        inputName.value = null
        inputEmail.value = null
    }

    companion object {
        private const val TAG = "FriendViewModel"
    }
}