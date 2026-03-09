package com.example.springlab2.domain.restaurant

import com.example.springlab2.domain.dish.DishEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "restaurants")
class RestaurantEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = false)
    var address: String = "",

    @OneToMany(mappedBy = "restaurant", cascade = [CascadeType.ALL], orphanRemoval = true)
    var dishes: MutableList<DishEntity> = mutableListOf()
) {
    constructor() : this(null, "", "", mutableListOf())
}

enum class OrderStatus {
    PENDING, CONFIRMED, DELIVERED, CANCELLED
}