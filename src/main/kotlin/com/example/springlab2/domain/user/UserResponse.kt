package com.example.springlab2.domain.user

data class UserResponse(
    val id: Long?,
    val email: String,
    val firstName: String,
    val lastName: String,
    val isActive: Boolean
)
