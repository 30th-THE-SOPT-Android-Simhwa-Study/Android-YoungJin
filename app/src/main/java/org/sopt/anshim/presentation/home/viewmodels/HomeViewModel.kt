package org.sopt.anshim.presentation.home.viewmodels

import androidx.lifecycle.ViewModel
import org.sopt.anshim.data.model.UserInfo

class HomeViewModel : ViewModel() {
    private var userInfo: UserInfo? = null

    fun setUserInfo(userInfo: UserInfo) {
        this.userInfo = userInfo
    }

    fun getUserInfo(): UserInfo? = userInfo

    companion object {
        private const val TAG = "HomeViewModel"
    }
}