package com.example.androidminiapp.db.room

class RegisterRepository(
    private val dao: UserDao,
) {
    val users = dao.getAllUsers()

    suspend fun insert(user: User) {
        return dao.insert(user)
    }

    suspend fun getEmail(email:String):User? {
        return dao.getEmail(email)
    }
}