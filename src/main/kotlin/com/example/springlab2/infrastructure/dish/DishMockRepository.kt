package com.example.springlab2.infrastructure.dish

import com.example.springlab2.application.dish.DishRepositoryPort
import com.example.springlab2.domain.dish.Dish

class DishMockRepository : DishRepositoryPort {
    private val storage = mutableMapOf<Long, Dish>()
    private var seq = 1L

    override fun getList(): List<Dish> {
        return storage.values.toList()
    }

    override fun create(dish: Dish): Dish {
        val saved = dish.copy(id = seq++)
        storage[saved.id as Long] = saved
        return saved
    }

    override fun findById(id: Long): Dish {
        return storage[id]!!
    }

    override fun deleteById(id: Long): Boolean {
        storage.remove(id)
        return true
    }

    // ...
}