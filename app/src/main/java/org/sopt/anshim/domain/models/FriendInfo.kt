package org.sopt.anshim.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friend_data_table")
data class FriendInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val email: String
)