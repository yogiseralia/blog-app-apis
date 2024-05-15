package com.yogeshseralia.blogappapis.repositories

import com.yogeshseralia.blogappapis.entities.Comment
import com.yogeshseralia.blogappapis.entities.Post
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepo : JpaRepository<Comment, Int> {
    fun findByPost(post: Post): List<Comment>
}