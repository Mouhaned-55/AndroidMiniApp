package com.example.androidminiapp.db.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insert(signup:User)

    @Query("SELECT * FROM user_table ORDER BY userId DESC")
    fun getAllUsers() : LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE email LIKE :email")
    suspend fun getEmail(email:String):User?

}