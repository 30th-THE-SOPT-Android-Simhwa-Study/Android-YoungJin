package org.sopt.anshim.domain.models

data class Restaurant(
    val id: Int,
    val name: String,
    val score: Float,
    var isScrap: Boolean? = false,
    val location: String? = null,
    val time: List<String>? = null,
    val contact: String? = null,
)
