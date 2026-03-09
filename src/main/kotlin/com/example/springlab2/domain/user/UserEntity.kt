package com.example.springlab2.domain.user

import com.example.springlab2.domain.order.OrderEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "users")
open class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null,

    @Column(name = "email", nullable = false)
    open var email: String = "",

    @Column(name = "first_name", nullable = false)
    open var firstName: String = "",

    @Column(name = "last_name", nullable = false)
    open var lastName: String = "",

    @Column(name = "is_active", nullable = false)
    open var isActive: Boolean = true,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    open var orders: MutableList<OrderEntity> = mutableListOf()
) {
    constructor() : this(null, "", "", "", true, mutableListOf())
    companion object
}