package com.example.springlab2.domain.dish

import com.example.springlab2.domain.restaurant.RestaurantEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "dishes")
class DishEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String = "",

    @Column(name = "description", nullable = false)
    var description: String = "",

    @Column(name = "price", nullable = false)
    var price: BigDecimal = BigDecimal.valueOf(0.0),

    @Column(name = "is_available", nullable = false)
    var isAvailable: Boolean = true,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    var restaurant: RestaurantEntity? = null
) {
    constructor() : this(null, "", "", BigDecimal.ZERO, true, null)
    companion object
}