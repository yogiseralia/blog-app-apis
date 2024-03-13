package com.yogeshseralia.blogappapis.controllers

import com.yogeshseralia.blogappapis.entities.User
import com.yogeshseralia.blogappapis.payloads.UserDto
import com.yogeshseralia.blogappapis.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
    @PutMapping("/{userId}")
    fun updateUser(@RequestBody userDto: UserDto, @PathVariable userId: Int): ResponseEntity<UserDto> {
        val updatedUser = userService.updateUser(userDto, userId)
        return ResponseEntity.ok(updatedUser)
    }

    //DELETE delete
    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable userId: Int): ResponseEntity<Map<String, String>> {
        userService.getDeleteUser(userId)
        return ResponseEntity<Map<String, String>>(mapOf("message" to "user deleted successfully"), HttpStatus.OK)
    }

    //GET getall
    @GetMapping("/{userId}")
    fun getUserById(@PathVariable userId: Int): ResponseEntity<UserDto> {
        return ResponseEntity.ok(userService.getUserById(userId))
    }

    //GET get
    @GetMapping("/")
    fun getAllUsers(): ResponseEntity<List<UserDto>> {
        return ResponseEntity.ok(userService.getAllUsers())
    }
}