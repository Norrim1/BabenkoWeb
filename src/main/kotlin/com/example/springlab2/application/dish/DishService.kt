package com.example.springlab2.application.dish

import com.example.springlab2.domain.dish.Dish
import org.springframework.stereotype.Service


@Service
class DishService (
    private val dishRepositoryPort: DishRepositoryPort
) {
    fun execute(cmd: CreateDishCommand): Dish {
        return dishRepositoryPort.create(
            Dish(
                id = null,
                name = cmd.name,
                description = cmd.description,
                price = cmd.price,
                isAvailable = true,
                restaurantId = cmd.restaurant,
            )
        )
    }

    fun update(cmd: CreateDishCommand, id: Long): Dish {
        return dishRepositoryPort.create(
            Dish(
                id = id,
                name = cmd.name,
                description = cmd.description,
                price = cmd.price,
                isAvailable = cmd.isAvailable,
                restaurantId = cmd.restaurant
            )
        )
    }

    fun getById(id: Long): Dish? {
        return dishRepositoryPort.findById(id)
    }

    fun getList(): List<Dish>{
        return dishRepositoryPort.getList()
    }

    fun deleteById(id: Long): Boolean {
        return dishRepositoryPort.deleteById(id)
    }
}