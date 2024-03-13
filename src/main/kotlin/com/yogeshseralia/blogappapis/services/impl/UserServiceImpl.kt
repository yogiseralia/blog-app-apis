package com.yogeshseralia.blogappapis.services.impl

import com.yogeshseralia.blogappapis.entities.User
import com.yogeshseralia.blogappapis.exceptions.ResourceNotFoundException
import com.yogeshseralia.blogappapis.payloads.UserDto
import com.yogeshseralia.blogappapis.repositories.UserRepository
import com.yogeshseralia.blogappapis.services.UserService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired(required = true)
    private lateinit var modelMapper: ModelMapper

    override fun createUser(userDto: UserDto): UserDto {
        val user = userDtoToUser(userDto)
        val savedUser = userRepository.save(user)
        return userToUserDto(savedUser)
    }

    override fun updateUser(userDto: UserDto, userId: Int): UserDto {
        val savedUser = userRepository.findById(userId).orElseThrow { ResourceNotFoundException("User", "id", userId) }
        savedUser.name = userDto.name
        savedUser.about = userDto.about
        savedUser.email = userDto.email
        savedUser.password = userDto.password
        val updatedUser = userRepository.save(savedUser)
        return userToUserDto(updatedUser)
    }

    override fun getUserById(userId: Int): UserDto {
        val savedUser = userRepository.findById(userId).orElseThrow { ResourceNotFoundException("User", "id", userId) }
        return userToUserDto(savedUser)
    }

    override fun getAllUsers(): List<UserDto> {
        val list: ArrayList<UserDto> = arrayListOf()
        userRepository.findAll().map { list.add(userToUserDto(it)) }
        return list
    }

    override fun getDeleteUser(userId: Int) {
        val savedUser = userRepository.findById(userId).orElseThrow { ResourceNotFoundException("User", "id", userId) }
        userRepository.delete(savedUser)
    }

    private fun userToUserDto(user: User): UserDto {
//        UserDto(user.id, user.name, user.email, user.password, user.about)
        return modelMapper.map(user, UserDto::class.java)
    }

    private fun userDtoToUser(user: UserDto): User {
//        User(user.id, user.name, user.email, user.password, user.about)
        return modelMapper.map(user, User::class.java)
    }
}