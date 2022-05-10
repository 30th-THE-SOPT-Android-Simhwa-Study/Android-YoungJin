package org.sopt.anshim.presentation.friend.viewmodels

import android.util.Patterns
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.sopt.anshim.domain.repositories.FriendRepository
import org.sopt.anshim.data.models.db.FriendInfo
import org.sopt.anshim.data.models.types.MBTI
import org.sopt.anshim.util.safeLet
import org.sopt.anshim.util.safeValueOf

class FriendViewModel(private val repository: FriendRepository) : ViewModel() {
    val friends = repository.getAll()
    private val selectedFriendInfo = MutableLiveData<FriendInfo?>()
    private val friendName = MutableLiveData<String?>()
    private val friendEmail = MutableLiveData<String?>()
    private val friendMBTI = MutableLiveData<String?>()
    private val isValidEmail = MutableLiveData<Boolean>()
    private val isUpdateMode = MediatorLiveData<Boolean?>()

    init {
        initUpdateMode()
    }

    private fun initUpdateMode() {
        isUpdateMode.addSource(friendName) { name ->
            combineUpdateMode(name, friendEmail.value)
        }
        isUpdateMode.addSource(friendEmail) { email ->
            combineUpdateMode(friendName.value, email)
        }
    }

    private fun combineUpdateMode(name: String?, email: String?) {
        // 선택한 friendInfo가 존재해도 입력된 name과 email을 모두 지운 경우, update mode를 false로 설정
        if (isUpdateMode.value == true && name?.isEmpty() == true && email?.isEmpty() == true) {
            isUpdateMode.value = false
        }
    }

    fun setSelectedFriendInfo(friend: FriendInfo) {
        isUpdateMode.value = true
        selectedFriendInfo.value = friend
        friendName.value = friend.name
        friendEmail.value = friend.email
    }

    fun onNameTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        friendName.value = s.toString().trim()
    }

    fun onEmailTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        friendEmail.value = s.toString().trim()
        checkEmailFormat()
    }

    fun onMBTITextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        friendMBTI.value = s.toString().trim()
    }

    private fun checkEmailFormat() {
        val emailPattern = Patterns.EMAIL_ADDRESS
        isValidEmail.value = emailPattern.matcher(friendEmail.value).matches()
    }

    fun saveFriend() {
        if (isUpdateMode.value == true) {
            updateFriend()
        } else {
            registerFriend()
        }
        clearFriendInput()
    }

    private fun registerFriend() {
        safeLet(friendName.value, friendEmail.value) { name, email ->
            viewModelScope.launch {
                repository.insert(FriendInfo(name, email, safeValueOf<MBTI>(friendMBTI.value?.uppercase())))
            }
        }
    }

    private fun updateFriend() {
        safeLet(
            selectedFriendInfo.value,
            friendName.value,
            friendEmail.value
        ) { friend, name, email ->
            viewModelScope.launch {
                repository.update(
                    FriendInfo(
                        name,
                        email,
                        safeValueOf<MBTI>(friendMBTI.value?.uppercase()),
                        friend.id
                    )
                )
            }
        }
        isUpdateMode.value = null
    }


    fun deleteFriend() {
        if (isUpdateMode.value == true) {
            deleteFriend(selectedFriendInfo.value)
        } else {
            deleteAllFriends()
        }
    }

    private fun deleteFriend(friend: FriendInfo?) {
        if (friend == null) return
        viewModelScope.launch {
            repository.delete(friend)
        }
        isUpdateMode.value = null
        clearFriendInput()
    }

    private fun deleteAllFriends() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    private fun clearFriendInput() {
        friendName.value = null
        friendEmail.value = null
        friendMBTI.value = null
    }

    fun getFriendName(): LiveData<String?> = friendName
    fun getFriendEmail(): LiveData<String?> = friendEmail
    fun getFriendMBTI(): LiveData<String?> = friendMBTI
    fun getValidEmail(): LiveData<Boolean?> = isValidEmail
    fun getUpdateMode(): LiveData<Boolean?> = isUpdateMode

    companion object {
        private const val TAG = "FriendViewModel"
    }
}