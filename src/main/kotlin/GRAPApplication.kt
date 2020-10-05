package com.grap

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GrapApplication

fun main(args: Array<String>) {
	runApplication<GrapApplication>(*args)
}
