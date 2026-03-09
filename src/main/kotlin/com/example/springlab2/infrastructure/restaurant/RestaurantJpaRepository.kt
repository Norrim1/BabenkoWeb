package com.example.springlab2.infrastructure.restaurant

import com.example.springlab2.domain.restaurant.RestaurantEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RestaurantJpaRepository: JpaRepository<RestaurantEntity, Long> {
}