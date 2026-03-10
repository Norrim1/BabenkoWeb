package com.example.springlab2.application.dish

import com.example.springlab2.domain.dish.Dish

interface DishRepositoryPort {
    fun getList(): List<Dish>
    fun create(dish: Dish): Dish
    fun findById(id: Long): Dish?
    fun deleteById(id: Long): Boolean
}