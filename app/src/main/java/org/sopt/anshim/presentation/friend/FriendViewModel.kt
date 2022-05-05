package org.sopt.anshim.presentation.friend

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.anshim.domain.FriendRepository
import org.sopt.anshim.domain.models.FriendInfo
import org.sopt.anshim.util.safeLet

class FriendViewModel(private val repository: FriendRepository) : ViewModel() {

    val friends = repository.friends
    private val friendName = MutableLiveData<String?>()
    private val friendEmail = MutableLiveData<String?>()
    private val isValidEmail = MutableLiveData<Boolean>()

    fun onNameTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        friendName.value = s.toString().trim()
    }

    fun onEmailTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        friendEmail.value = s.toString().trim()
        checkEmailFormat()
    }

    private fun checkEmailFormat() {
        val emailPattern = Patterns.EMAIL_ADDRESS
        isValidEmail.value = emailPattern.matcher(friendEmail.value).matches()
    }

    fun saveFriend() {
        viewModelScope.launch {
            safeLet(friendName.value, friendEmail.value) { name, email ->
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
        friendName.value = null
        friendEmail.value = null
    }

    fun getFriendName(): LiveData<String?> = friendName
    fun getFriendEmail(): LiveData<String?> = friendEmail
    fun getValidEmail(): LiveData<Boolean?> = isValidEmail

    companion object {
        private const val TAG = "FriendViewModel"
    }
}