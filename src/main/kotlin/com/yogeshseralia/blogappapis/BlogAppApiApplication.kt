package com.yogeshseralia.blogappapis

import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SpringBootApplication
class BlogAppApiApplication {
	@Bean
	fun modelMapper(): ModelMapper = ModelMapper()
}

fun main(args: Array<String>) {
	runApplication<BlogAppApiApplication>(*args)
}
