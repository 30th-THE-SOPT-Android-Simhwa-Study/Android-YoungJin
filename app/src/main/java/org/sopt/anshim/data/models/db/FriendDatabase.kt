package org.sopt.anshim.data.models.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FriendInfo::class], version = 2)
abstract class FriendDatabase : RoomDatabase() {
    abstract fun friendDao(): FriendDao
}