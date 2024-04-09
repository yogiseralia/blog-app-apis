package com.yogeshseralia.blogappapis.repositories

import com.yogeshseralia.blogappapis.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepo : JpaRepository<User, Int>