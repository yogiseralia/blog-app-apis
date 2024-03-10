package com.yogeshseralia.blogappapis.entities

import jakarta.persistence.*
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Entity
@Table(name = "users")
@NoArgsConstructor
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int,
        @Column(name = "user_name", nullable = false, length = 100)
        var name: String,
        var email: String,
        var password: String,
        var about: String
)