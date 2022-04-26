package org.sopt.anshim.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val name: String,
    val mbti: String,
    val email: String
) : Parcelable