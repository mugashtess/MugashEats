package com.mugash.mugasheats.repository

import com.mugash.mugasheats.data.UserDao
import com.mugash.mugasheats.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User) {
        userDao.registerUser(user)
    }

    suspend fun loginUser(email: String, password: String): User? {
        return userDao.loginUser(email, password)
    }
}