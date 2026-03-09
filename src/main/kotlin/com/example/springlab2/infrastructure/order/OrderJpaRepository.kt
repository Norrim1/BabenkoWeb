package com.example.springlab2.infrastructure.order

import com.example.springlab2.domain.order.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderJpaRepository : JpaRepository<OrderEntity, Long> {
}