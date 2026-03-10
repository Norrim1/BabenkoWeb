package com.example.springlab2.application.user

import com.example.springlab2.domain.user.User
import com.example.springlab2.domain.user.UserCreateRequest
import com.example.springlab2.domain.user.UserResponse
import com.example.springlab2.domain.user.UserUpdateRequest
import com.example.springlab2.infrastructure.user.toCommand
import com.example.springlab2.infrastructure.user.toResponse
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {
    @GetMapping
    fun listUsers(): ResponseEntity<Any> {

        val userlist = userService.getList()

        return if (!userlist.isEmpty()) {
            ResponseEntity.ok(userlist)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createUser(
        @RequestBody request: UserCreateRequest
    ): ResponseEntity<UserResponse> {

        val user = userService.execute(request.toCommand())

        return ResponseEntity
            .status(201)
            .body(user.toResponse())
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<Any> {

        val user = userService.getById(id)

        return (if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.notFound().build()
        })
    }

    @PutMapping("/{id}")
    fun putUserById(@PathVariable id: Long, @RequestBody request: UserUpdateRequest): ResponseEntity<Any> {

        var user = userService.getById(id)

        return (if (user != null) {
            user = userService.update(request.toCommand(), id)
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.notFound().build()
        })
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable id: Long): ResponseEntity<Any> {

        val user = userService.getById(id)

        return (if (user != null) {
            userService.deleteById(id)
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.notFound().build()
        })
    }
}
