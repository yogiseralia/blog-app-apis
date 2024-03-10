package com.yogeshseralia.blogappapis.repositories

import com.yogeshseralia.blogappapis.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int>