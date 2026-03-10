package com.example.springlab2.application.dish

import com.example.springlab2.domain.restaurant.RestaurantEntity
import java.math.BigDecimal

/**
 * DTO for [com.example.springlab2.domain.dish.DishEntity]
 */
data class CreateDishCommand(
    val id: Long?,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val isAvailable: Boolean,
    val restaurant: RestaurantEntity?,
)