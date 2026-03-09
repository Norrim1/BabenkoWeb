package com.example.springlab2.infrastructure.user

import com.example.springlab2.application.user.CreateUserCommand
import com.example.springlab2.domain.dish.Dish
import com.example.springlab2.domain.user.User
import com.example.springlab2.domain.user.UserCreateRequest
import com.example.springlab2.domain.user.UserEntity
import com.example.springlab2.domain.user.UserResponse
import com.example.springlab2.domain.user.UserUpdateRequest

fun UserEntity.toDomain(): User =
    User(
        id = this.id!!,
        email = this.email,
        firstName = this.firstName,
        lastName = this.lastName,
        isActive = this.isActive
    )

fun UserEntity.Companion.fromDomain(user: User): UserEntity =
    UserEntity(
        id = user.id,
        email = user.email,
        firstName = user.firstName,
        lastName = user.lastName,
        isActive = user.isActive
    )

fun UserEntity.toCreateUserCommand() = CreateUserCommand(
    id = this.id,
    email = this.email,
    firstName = this.firstName,
    lastName = this.lastName,
    isActive = this.isActive
)

fun CreateUserCommand.toEntity() = UserEntity().also {
    it.id = this.id
    it.email = this.email
    it.firstName = this.firstName
    it.lastName = this.lastName
    it.isActive = this.isActive
}

fun UserCreateRequest.toCommand() = CreateUserCommand(
    email = this.email,
    firstName = this.firstName,
    lastName = this.lastName,
    id = 0,
    isActive = true
)

fun User.toResponse() = UserResponse(
    id = this.id,
    email = this.email,
    firstName = this.firstName,
    lastName = this.lastName,
    isActive = this.isActive
)

fun UserUpdateRequest.toCommand() = CreateUserCommand(
    email = this.email,
    firstName = this.firstName,
    lastName = this.lastName,
    id = 0,
    isActive = this.isActive
)