package org.sopt.anshim.data.models.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FriendInfo::class], version = 2)
abstract class FriendDatabase : RoomDatabase() {

    abstract fun friendDAO(): FriendDAO

    companion object {
        @Volatile
        private var instance: FriendDatabase? = null
        fun getInstance(context: Context): FriendDatabase {
            synchronized(this) {
                return instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    FriendDatabase::class.java,
                    "friend_data_database"
                ).build()
            }
        }
    }
}