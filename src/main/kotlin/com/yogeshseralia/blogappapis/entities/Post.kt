package com.yogeshseralia.blogappapis.entities

import jakarta.persistence.*
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.util.*

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "posts")
data class Post(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,
        @Column(name = "post_title", length = 100, nullable = false)
        var title: String = "",
        @Column(length = 10_000)
        var content: String = "",
        var imageName: String = "",
        var addDate: Date = Date(),
        @ManyToOne
        var user: User = User(),
        @ManyToOne
        @JoinColumn(name = "category_id")
        var category: Category = Category()
)