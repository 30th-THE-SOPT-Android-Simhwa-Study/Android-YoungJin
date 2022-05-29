package org.sopt.anshim.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val name: String,
    val mbti: String? = null,
    val email: String? = null,
    val profile: String? = null,
    val location: String? = null
) : Parcelable