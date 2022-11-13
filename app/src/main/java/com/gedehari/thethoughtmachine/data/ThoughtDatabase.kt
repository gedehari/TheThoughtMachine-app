package com.gedehari.thethoughtmachine.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Thought::class], version = 1, exportSchema = false)
abstract class ThoughtDatabase : RoomDatabase() {
    abstract fun thoughtDao(): ThoughtDao

    companion object {
        @Volatile
        private var instance: ThoughtDatabase? = null

        fun getDatabase(context: Context): ThoughtDatabase {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    ThoughtDatabase::class.java,
                    "thought_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                instance = newInstance
                return newInstance
            }
        }
    }
}
