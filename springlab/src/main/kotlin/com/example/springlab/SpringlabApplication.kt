package com.example.springlab

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringlabApplication

fun main(args: Array<String>) {
	runApplication<SpringlabApplication>(*args)
}
