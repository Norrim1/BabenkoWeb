package com.example.springlab.controller

import com.example.springlab.dto.CreateUserRequest
import com.example.springlab.dto.GreetingMain
import com.example.springlab.dto.GreetingUser
import com.example.springlab.dto.UserData
import com.example.springlab.dto.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/greeting")
class GreetingController(
    private val userService: UserService
) {
    @GetMapping
    fun greeting(
        @RequestParam(required = false) id: UUID?
    ): ResponseEntity<Any> {

        if (id == null) {
            val greeting = GreetingMain()
            return ResponseEntity.ok(greeting)
        }

        val user = userService.findById(id)

        return if(user == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(user)
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody request: CreateUserRequest): GreetingUser {
        val user = userService.create(request.name, request.surname)
        return GreetingUser(
            "Hello, ${user.name} ${user.surname}",
            user.id
        )
    }

    @GetMapping
    @RequestMapping("/{id}")
    fun greetingUser(@PathVariable id: UUID): ResponseEntity<UserData> {
        val user = userService.findById(id)

        return if(user == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(user)
        }
    }
}