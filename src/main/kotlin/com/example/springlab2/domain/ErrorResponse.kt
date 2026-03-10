package com.example.springlab2.domain

data class ErrorResponse(
    val status: Int,
    val error: String,
    val message: String
)