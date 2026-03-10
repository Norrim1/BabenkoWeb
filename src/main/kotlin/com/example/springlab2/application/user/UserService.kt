package com.example.springlab2.application.user

import com.example.springlab2.domain.user.User
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepositoryPort: UserRepositoryPort
) {
    fun execute(cmd: CreateUserCommand): User {
        return userRepositoryPort.create(
            User(
                id = null,
                email = cmd.email,
                firstName = cmd.firstName,
                lastName = cmd.lastName,
                isActive = true
            )
        )
    }

    fun update(cmd: CreateUserCommand, id: Long): User {
        return userRepositoryPort.create(
            User(
                id = id,
                email = cmd.email,
                firstName = cmd.firstName,
                lastName = cmd.lastName,
                isActive = cmd.isActive
            )
        )
    }
    fun getById(id: Long): User? {
        return userRepositoryPort.findById(id)
    }

    fun getList(): List<User>{
        return userRepositoryPort.getList()
    }

    fun deleteById(id: Long): Boolean {
        return userRepositoryPort.deleteById(id)
    }


    /*fun getByEmail(email: String): Boolean {
        return userRepositoryPort.getByEmail(email)
    }*/
}