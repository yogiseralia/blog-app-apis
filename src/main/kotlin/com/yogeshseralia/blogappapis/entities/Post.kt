package com.yogeshseralia.blogappapis.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "posts")
data class Post(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,
        @Column(name = "post_title", length = 100, nullable = false)
        val title: String,
        @Column(length = 10_000)
        val content: String,
        val imageName: String,
        val addDate: Date,
        @ManyToOne
        val user: User,
        @ManyToOne
        @JoinColumn(name = "category_id")
        val category: Category
)