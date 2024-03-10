package com.yogeshseralia.blogappapis.services

import com.yogeshseralia.blogappapis.payloads.UserDto

interface UserService {
    fun createUser(userDto: UserDto): UserDto
    fun updateUser(userDto: UserDto, userId: Int): UserDto
    fun getUserById(userId: Int): UserDto
    fun getAllUsers(): List<UserDto>
    fun getDeleteUser(userId: Int)
}