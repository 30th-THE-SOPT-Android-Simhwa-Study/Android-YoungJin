package org.sopt.anshim.data.models.github

import com.google.gson.annotations.SerializedName

data class OrgInfo(
    @SerializedName("login")
    val name: String,
    @SerializedName("avatar_url")
    val profile: String,
)