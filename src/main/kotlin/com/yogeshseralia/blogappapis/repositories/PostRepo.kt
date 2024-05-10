package com.yogeshseralia.blogappapis.repositories

import com.yogeshseralia.blogappapis.entities.Category
import com.yogeshseralia.blogappapis.entities.Post
import com.yogeshseralia.blogappapis.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PostRepo : JpaRepository<Post, Int> {
    fun findByUser(user: User): List<Post>
    fun findByCategory(category: Category): List<Post>
    fun findByTitleContaining(title: String): List<Post>
    @Query("SELECT p FROM Post p WHERE p.content LIKE :key")
    fun searchByContent(key: String): List<Post>
}