package com.example.springlab2.application.user

/**
 * DTO for [com.example.springlab2.domain.user.UserEntity]
 */
data class CreateUserCommand(
    val id: Long?,
    val email: String,
    val firstName: String,
    val lastName: String,
    val isActive: Boolean
)