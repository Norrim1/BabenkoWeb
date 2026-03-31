package com.example.springlab2.domain.user

data class UserCreateRequest(
    val email: String,
    val firstName: String,
    val lastName: String,
    val isActive: Boolean = true
)
