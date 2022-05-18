package org.sopt.anshim.data.models.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import org.sopt.anshim.data.models.types.Mbti

@Parcelize
@Entity(tableName = "friend_data_table")
data class FriendInfo(
    val name: String,
    val email: String,
    val mbti: Mbti?,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
): Parcelable