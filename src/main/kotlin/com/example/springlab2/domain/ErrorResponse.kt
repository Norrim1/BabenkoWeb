package com.example.springlab2.domain

import java.time.LocalDateTime

open class ErrorResponse(
    val status: Int,
    val message: String? = null,
    val timestamp: LocalDateTime = LocalDateTime.now()
)

class ValidationErrorResponse(
    status: Int,
    message: String? = null,
    val errors: Map<String, String>,
    timestamp: LocalDateTime = LocalDateTime.now()
) : ErrorResponse(status, message, timestamp)

sealed class AppException(message: String) : RuntimeException(message)

class NotFoundException(message: String) : AppException(message)

class AlreadyExistsException(message: String) : AppException(message)

class InvalidOrderStateException(message: String) : AppException(message)