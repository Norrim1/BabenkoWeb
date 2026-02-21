package com.example.springlab.dto

import java.util.UUID

data class UserData(
    val id: UUID,
    val name: String,
    val surname: String
)
