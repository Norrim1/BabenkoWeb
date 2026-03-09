package com.example.springlab2.application.user

import com.example.springlab2.domain.user.User

interface UserRepositoryPort {
    fun create(user: User): User
    fun findById(id: Long): User?
    fun getList(): List<User>
    fun deleteById(id: Long): Boolean
    /*fun getByEmail(): Boolean*/
}