package com.example.androidminiapp.db.room

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class RegisterDatabase : RoomDatabase() {

    abstract val registerDatabaseDao : UserDao

    companion object {
        @Volatile
        private var INSTANCE : RegisterDatabase? = null

        fun getInstance(context: Context) : RegisterDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RegisterDatabase::class.java,
                        "user_details_database"
                    ).fallbackToDestructiveMigration().build()
                }
                return instance
            }
        }
    }


}