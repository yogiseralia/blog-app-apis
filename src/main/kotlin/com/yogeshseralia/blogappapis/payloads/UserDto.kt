package com.yogeshseralia.blogappapis.payloads

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class UserDto(
        val id: Int,
        @field:NotNull
        @field:NotEmpty
        @Size(min = 4, message = "User name must be greater than 4 chars")
        val name: String,

        @field:NotNull
        @field:NotEmpty
        @field:Email(message = "User email is not valid")
        val email: String,

        @field:NotNull
        @field:NotEmpty
        @field:Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\d)(?=.*?[\\W_]).{8,16}\$", message = "password must contain atleast 1 capital char, 1 small char, 1 special char, 1 numeric char and length between 8 and 16")
        val password: String,

        @field:NotNull
        @field:NotEmpty
        val about: String
)