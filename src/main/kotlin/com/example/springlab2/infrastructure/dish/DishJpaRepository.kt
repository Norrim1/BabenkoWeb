package com.example.springlab2.infrastructure.dish

import com.example.springlab2.domain.dish.Dish
import com.example.springlab2.domain.dish.DishEntity
import org.springframework.data.jpa.repository.JpaRepository


interface DishJpaRepository : JpaRepository<DishEntity, Long> {
    fun findByRestaurantId(restaurantId: Long?): MutableList<Dish?>
}