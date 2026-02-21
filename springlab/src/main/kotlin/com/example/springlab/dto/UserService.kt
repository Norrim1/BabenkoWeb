package com.example.springlab.dto

import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserService {
    private val users = mutableMapOf(
        UUID.fromString("550e8400-e29b-41d4-a716-446655440000") to UserData(
            id = UUID.fromString("550e8400-e29b-41d4-a716-446655440000"),
            name = "Ivan",
            surname = "Ivanov"
        )
    )

    fun findById(id: UUID): UserData? {
        return users[id]
    }
    fun create(name: String, surname: String): UserData {
        val id = UUID.randomUUID()
        val user = UserData(id, name, surname)
        users[id] = user
        return user
    }
}