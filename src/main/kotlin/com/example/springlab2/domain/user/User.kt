package com.example.springlab2.domain.user

data class User(
    val id: Long?,
    val email: String,
    val firstName: String,
    val lastName: String,
    val isActive: Boolean
)
