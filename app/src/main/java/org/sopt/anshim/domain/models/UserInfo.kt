package org.sopt.anshim.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val name: String,
    val mbti: String,
    val email: String
) : Parcelable