package com.example.springlab2.infrastructure.user

import com.example.springlab2.application.user.UserRepositoryPort
import com.example.springlab2.domain.user.User
import com.example.springlab2.domain.user.UserEntity
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

@Component
class UserJpaAdapter(
    private val userJpaRepository: UserJpaRepository
) : UserRepositoryPort {
    override fun create(user: User): User =
        userJpaRepository.save(UserEntity.fromDomain(user)).toDomain()

    override fun findById(id: Long): User {
        val entity = userJpaRepository.findById(id).getOrNull()
            ?: throw NoSuchElementException("User not found")

        return entity.toDomain()
    }

    override fun getList(): List<User> {
        val users = userJpaRepository.findAll()
        for (user in users)
            user.toDomain()
        return users as List<User>
    }

    override fun deleteById(id: Long): Boolean {
        val entity = userJpaRepository.findById(id).getOrNull()
            ?: throw NoSuchElementException("User not found")
        userJpaRepository.delete(entity)
        return true
    }

    /*override fun getByEmail(): Boolean {
        val finded = false
        val users = userJpaRepository.findAll()
        for (user in users)
            user.toDomain()
        users.
    }*/
}
