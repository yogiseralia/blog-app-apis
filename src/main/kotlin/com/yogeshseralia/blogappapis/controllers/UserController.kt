package com.yogeshseralia.blogappapis.controllers

import com.yogeshseralia.blogappapis.payloads.UserDto
import com.yogeshseralia.blogappapis.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController {

    @Autowired
    lateinit var userService: UserService

    //POST create
    @PostMapping("/")
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<UserDto> {
        val createdUser = userService.createUser(userDto)
        return ResponseEntity(createdUser, HttpStatus.CREATED)
    }

    //PUT update
    //DELETE delete
    //GET get
    //GET getall
}