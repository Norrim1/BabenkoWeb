package com.example.springlab2.infrastructure.user

import com.example.springlab2.domain.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<UserEntity, Long> {
}