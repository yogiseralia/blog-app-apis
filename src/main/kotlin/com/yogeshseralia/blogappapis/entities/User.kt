package com.yogeshseralia.blogappapis.entities

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        var id: Int = 0,
        @Column(name = "user_name", nullable = false, length = 100)
        var name: String = "",
        var email: String = "",
        var password: String = "",
        var about: String = "",
        @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        private val posts: List<Post> = arrayListOf()
)