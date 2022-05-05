package org.sopt.anshim.presentation.sign.viewmodels

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.anshim.domain.models.UserInfo
import java.util.regex.Pattern

class SignViewModel : ViewModel() {
    private var userInfo: UserInfo? = null
    private val userEmail = MutableLiveData<String>()
    private val userPassword = MutableLiveData<String>()
    private val isValidEmail = MutableLiveData<Boolean>()
    private val isValidPassword = MutableLiveData<Boolean>()
    private val isEnabledLoginButton = MediatorLiveData<Boolean>()
    private var isCompletedSignIn = MutableLiveData<Boolean>()

    init {
        initEnabledLoginButton()
    }

    fun signIn() {
        if (userEmail.value == null || userPassword.value == null) return

        userInfo = UserInfo(
            name = "최영진",
            mbti = "ISFP",
            email = userEmail.value!!
        )

        // TODO implement login process
        isCompletedSignIn.value = true
    }

    fun onEmailTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        userEmail.value = s.toString().trim()
        checkEmailFormat()
    }

    fun onPasswordTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        userPassword.value = s.toString().trim()
        checkPasswordFormat()
    }

    private fun checkEmailFormat() {
        val emailPattern = Patterns.EMAIL_ADDRESS
        isValidEmail.value = emailPattern.matcher(userEmail.value).matches()
    }

    private fun checkPasswordFormat() {
        val passwordPattern =
            Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{7,15}.$")
        isValidPassword.value = passwordPattern.matcher(userPassword.value).matches()
    }

    private fun initEnabledLoginButton() {
        isEnabledLoginButton.addSource(isValidEmail) {
            isEnabledLoginButton.value = combineEnabledLoginButton(it, isValidPassword.value)
        }
        isEnabledLoginButton.addSource(isValidPassword) {
            isEnabledLoginButton.value = combineEnabledLoginButton(isValidEmail.value, it)
        }
    }

    private fun combineEnabledLoginButton(
        isValidEmail: Boolean?,
        isValidPassword: Boolean?
    ): Boolean {
        if (isValidEmail == null || isValidPassword == null) return false
        return isValidEmail && isValidPassword
    }

    fun getUserEmail(): LiveData<String> = userEmail
    fun getUserPassword(): LiveData<String> = userPassword
    fun getValidEmail(): LiveData<Boolean?> = isValidEmail
    fun getValidPassword(): LiveData<Boolean?> = isValidPassword
    fun getEnabledLoginButton(): LiveData<Boolean?> = isEnabledLoginButton
    fun getCompleteSignIn(): LiveData<Boolean> = isCompletedSignIn
    fun getUserInfo(): UserInfo? = userInfo

    companion object {
        private const val TAG = "SignViewModel"
    }
}