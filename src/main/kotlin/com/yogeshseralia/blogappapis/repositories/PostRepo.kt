package com.yogeshseralia.blogappapis.repositories

import com.yogeshseralia.blogappapis.entities.Category
import com.yogeshseralia.blogappapis.entities.Post
import com.yogeshseralia.blogappapis.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepo : JpaRepository<Post, Int> {
    fun findByUser(user: User): List<Post>
    fun findByCategory(category: Category): List<Post>
}