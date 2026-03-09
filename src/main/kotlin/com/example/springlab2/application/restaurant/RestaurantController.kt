package com.example.springlab2.application.restaurant

import com.example.springlab2.domain.dish.Dish
import com.example.springlab2.domain.dish.DishEntity
import com.example.springlab2.domain.restaurant.RestaurantEntity
import com.example.springlab2.infrastructure.dish.DishJpaRepository
import com.example.springlab2.infrastructure.dish.fromDomain
import com.example.springlab2.infrastructure.dish.toDomain
import com.example.springlab2.infrastructure.restaurant.RestaurantJpaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*


@RestController
@RequestMapping("/restaurant")
class RestaurantController(private val restaurantJpaRepository: RestaurantJpaRepository,
                           private val dishJpaRepository: DishJpaRepository
) {

    @GetMapping
    fun getAll(pageable: Pageable): PagedModel<RestaurantEntity> {
        val restaurantEntities: Page<RestaurantEntity> = restaurantJpaRepository.findAll(pageable)
        return PagedModel(restaurantEntities)
    }

    @PostMapping
    fun create(@RequestBody restaurantEntity: RestaurantEntity): RestaurantEntity =
        restaurantJpaRepository.save(restaurantEntity)

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long): RestaurantEntity {
        val restaurantEntityOptional: Optional<RestaurantEntity> = restaurantJpaRepository.findById(id)
        return restaurantEntityOptional.orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `$id` not found")
        }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody restaurantEntity: RestaurantEntity): RestaurantEntity {
        if (!restaurantJpaRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `$id` not found")
        }
        return restaurantJpaRepository.save(restaurantEntity)
    }


    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): RestaurantEntity? {
        val restaurantEntity: RestaurantEntity? = restaurantJpaRepository.findById(id).orElse(null)
        if (restaurantEntity != null) {
            restaurantJpaRepository.delete(restaurantEntity)
        }
        return restaurantEntity
    }

    @GetMapping("/{id}/dishes")
    fun getRestaurantDishes(@PathVariable id: Long): MutableList<Dish?> {
        val restaurant = restaurantJpaRepository.findById(id)
            .orElseThrow({ RuntimeException("Restaurant not found") })

        return dishJpaRepository.findByRestaurantId(restaurant.id)
    }

    @PostMapping("/{restaurantId}/dishes")
    fun createDishInRestaurant(
        @PathVariable restaurantId: Long,
        @RequestBody dish: Dish
    ): ResponseEntity<Any> {
        val restaurant = restaurantJpaRepository.findById(restaurantId)
            .orElseThrow({ java.lang.RuntimeException("Restaurant not found") })

        dish.restaurantId = restaurant

        val savedDish: Dish? = dishJpaRepository.save(DishEntity.fromDomain(dish)).toDomain()

        return ResponseEntity.status(HttpStatus.CREATED).body(savedDish)
    }

}