package com.example.springlab2.infrastructure.user

import com.example.springlab2.application.user.UserRepositoryPort
import com.example.springlab2.domain.user.User

class UserMockRepository : UserRepositoryPort {
    private val storage = mutableMapOf<Long, User>()
    private var seq = 1L

    override fun create(user: User): User {
        val saved = user.copy(id = seq++)
        storage[saved.id!!] = saved
        return saved
    }

    override fun findById(id: Long): User {
        return storage[id]!!
    }

    override fun getList(): List<User> {
        return storage.values.toList()
    }

    override fun deleteById(id: Long): Boolean {
        storage.remove(id)
        return true
    }

    // ...
}