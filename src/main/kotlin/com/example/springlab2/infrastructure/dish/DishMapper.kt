package com.example.springlab2.infrastructure.dish

import com.example.springlab2.application.dish.CreateDishCommand
import com.example.springlab2.domain.dish.Dish
import com.example.springlab2.domain.dish.DishCreateRequest
import com.example.springlab2.domain.dish.DishEntity
import com.example.springlab2.domain.dish.DishResponse
import com.example.springlab2.domain.dish.DishUpdateRequest

fun DishEntity.toDomain(): Dish =
    Dish(
        id = this.id!!,
        name = this.name,
        description = this.description,
        price = this.price,
        isAvailable = this.isAvailable,
        restaurantId = this.restaurant
    )

fun DishEntity.Companion.fromDomain(dish: Dish): DishEntity =
    DishEntity(
        id = dish.id,
        name = dish.name,
        description = dish.description,
        price = dish.price,
        isAvailable = dish.isAvailable
    )

fun DishEntity.toCreateDishCommand() = CreateDishCommand(
    id = this.id,
    name = this.name,
    description = this.description,
    price = this.price,
    isAvailable = this.isAvailable,
    restaurant = this.restaurant
)

fun CreateDishCommand.toEntity() = DishEntity().also {
    it.id = this.id
    it.name = this.name
    it.description = this.description
    it.price = this.price
    it.isAvailable = this.isAvailable
}

fun DishCreateRequest.toCommand() = CreateDishCommand(
    name = this.name,
    description = this.description,
    price = this.price,
    id = 0,
    isAvailable = true,
    restaurant = this.restaurantId
)

fun Dish.toEntity() {

}

fun Dish.toResponse() = DishResponse(
    id = this.id,
    name = this.name,
    description = this.description,
    price = this.price,
    isAvailable = this.isAvailable,
    restaurantId = this.restaurantId
)

fun DishUpdateRequest.toCommand() = CreateDishCommand(
    name = this.name,
    price = this.price,
    description = this.description,
    id = 0,
    isAvailable = this.isAvailable,
    restaurant = this.restaurantId
)