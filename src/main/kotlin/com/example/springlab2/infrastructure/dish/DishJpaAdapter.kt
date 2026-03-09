package com.example.springlab2.infrastructure.dish

import com.example.springlab2.application.dish.DishRepositoryPort
import com.example.springlab2.domain.dish.Dish
import com.example.springlab2.domain.dish.DishEntity
import com.example.springlab2.domain.user.User
import com.example.springlab2.infrastructure.user.UserJpaRepository
import com.example.springlab2.infrastructure.user.toDomain
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

@Component
class DishJpaAdapter (
    private val dishJpaRepository: DishJpaRepository
) : DishRepositoryPort {

    override fun getList(): List<Dish> {
        val dishes = dishJpaRepository.findAll()
        for (dish in dishes)
            dish.toDomain()
        return dishes as List<Dish>
    }

    override fun create(dish: Dish): Dish =
        dishJpaRepository.save(DishEntity.fromDomain(dish)).toDomain()

    override fun findById(id: Long): Dish {
        val entity = dishJpaRepository.findById(id).getOrNull()
            ?: throw NoSuchElementException("Dish not found")

        return entity.toDomain()
    }

    override fun deleteById(id: Long): Boolean {
        val entity = dishJpaRepository.findById(id).getOrNull()
            ?: throw NoSuchElementException("Dish not found")
        dishJpaRepository.delete(entity)
        return true
    }
}