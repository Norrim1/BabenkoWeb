package com.example.springlab2.application.order

import com.example.springlab2.domain.order.OrderEntity
import com.example.springlab2.infrastructure.order.OrderJpaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedModel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import tools.jackson.databind.JsonNode
import tools.jackson.databind.ObjectMapper
import java.io.IOException
import java.util.Optional

@RestController
@RequestMapping("/orders")
class OrderController(private val orderJpaRepository: OrderJpaRepository, private val objectMapper: ObjectMapper)
{
    @GetMapping
    fun getAll(pageable: Pageable): PagedModel<OrderEntity> {
        val orderEntities: Page<OrderEntity> = orderJpaRepository.findAll(pageable)
        return PagedModel(orderEntities)
    }

    @PostMapping
    fun create(@RequestBody orderEntity: OrderEntity): OrderEntity = orderJpaRepository.save(orderEntity)

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long): OrderEntity {
        val orderEntityOptional: Optional<OrderEntity> = orderJpaRepository.findById(id)
        return orderEntityOptional.orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `$id` not found")
        }
    }

    @PatchMapping("/{id}/status")
    @Throws(IOException::class)
    fun patch(@PathVariable id: Long, @RequestBody patchNode: JsonNode): OrderEntity {
        val orderEntity: OrderEntity = orderJpaRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `$id` not found")
        }
        objectMapper.readerForUpdating(orderEntity).readValue<OrderEntity>(patchNode)
        return orderJpaRepository.save(orderEntity)
    }

}
