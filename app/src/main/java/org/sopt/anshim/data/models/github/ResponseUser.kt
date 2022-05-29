package org.sopt.anshim.data.models.github

import com.google.gson.annotations.SerializedName
import org.sopt.anshim.domain.models.UserInfo

data class ResponseUser(
    val id: Int,
    @SerializedName("login")
    val name: String,
    @SerializedName("avatar_url")
    val profile: String,
    val email: String,
    val location: String,
) {
    fun toUserInfo(user: ResponseUser): UserInfo =
        UserInfo(user.name, null, "", user.profile, user.location) // TODO email null 값 처리 필요
}