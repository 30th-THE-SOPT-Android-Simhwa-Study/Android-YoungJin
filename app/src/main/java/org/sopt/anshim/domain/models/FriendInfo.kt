package org.sopt.anshim.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friend_data_table")
data class FriendInfo(
    val name: String,
    val email: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)