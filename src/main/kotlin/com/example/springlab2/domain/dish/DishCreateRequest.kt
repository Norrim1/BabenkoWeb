package com.example.springlab2.domain.dish

import com.example.springlab2.domain.restaurant.RestaurantEntity
import java.math.BigDecimal

data class DishCreateRequest(
    val name: String,
    val description: String,
    val price: BigDecimal,
    val isAvailable: Boolean = true,
    val restaurantId: RestaurantEntity
)
