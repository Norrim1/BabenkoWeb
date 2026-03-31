package com.example.springlab2.application.dish

import com.example.springlab2.application.user.UserService
import com.example.springlab2.domain.dish.DishCreateRequest
import com.example.springlab2.domain.dish.DishResponse
import com.example.springlab2.domain.dish.DishUpdateRequest
import com.example.springlab2.domain.user.UserCreateRequest
import com.example.springlab2.domain.user.UserResponse
import com.example.springlab2.domain.user.UserUpdateRequest
import com.example.springlab2.infrastructure.dish.toCommand
import com.example.springlab2.infrastructure.dish.toResponse
import com.example.springlab2.infrastructure.user.toCommand
import com.example.springlab2.infrastructure.user.toResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dishes")
class DishController(
    private val dishService: DishService
) {
    @GetMapping
    fun listDishes(): ResponseEntity<Any> {

        val dishlist = dishService.getList()

        return if (!dishlist.isEmpty()) {
            ResponseEntity.ok(dishlist)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createDish(
        @RequestBody request: DishCreateRequest
    ): ResponseEntity<DishResponse> {

        val dish = dishService.execute(request.toCommand())

        return ResponseEntity
            .status(201)
            .body(dish.toResponse())
    }

    @GetMapping("/{id}")
    fun getDishById(@PathVariable id: Long): ResponseEntity<Any> {

        val dish = dishService.getById(id)

        return (if (dish != null) {
            ResponseEntity.ok(dish)
        } else {
            ResponseEntity.notFound().build()
        })
    }

    @PutMapping("/{id}")
    fun putDishById(@PathVariable id: Long, @RequestBody request: DishUpdateRequest): ResponseEntity<Any> {

        var dish = dishService.getById(id)

        return (if (dish != null) {
            dish = dishService.update(request.toCommand(), id)
            ResponseEntity.ok(dish)
        } else {
            ResponseEntity.notFound().build()
        })
    }

    @DeleteMapping("/{id}")
    fun deleteDishById(@PathVariable id: Long): ResponseEntity<Any> {

        val dish = dishService.getById(id)

        return (if (dish != null) {
            dishService.deleteById(id)
            ResponseEntity.ok(dish)
        } else {
            ResponseEntity.notFound().build()
        })
    }
}