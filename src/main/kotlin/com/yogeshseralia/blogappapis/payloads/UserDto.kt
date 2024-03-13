package com.yogeshseralia.blogappapis.payloads

data class UserDto(
        val id: Int,
        val name: String,
        val email: String,
        val password: String,
        val about: String
) {
    constructor() : this(0, "", "", "", "")
}