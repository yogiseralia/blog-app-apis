package com.yogeshseralia.blogappapis.entities

import jakarta.persistence.*
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
data class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val categoryId: Int,
        @Column(name = "title", length = 100, nullable = false)
        var categoryTitle: String,
        @Column(name = "description")
        var categoryDescription: String,

        @OneToMany(mappedBy = "category", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.LAZY)
        private val posts: List<Post> = arrayListOf()
)