package com.yogeshseralia.blogappapis.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.util.*

@NoArgsConstructor
@Getter
@Setter
@Table(name = "comments")
@Entity
data class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @Column(length = 1_000)
    var comment: String,
    var addDate: Date = Date(),
    @ManyToOne
    @JoinColumn(name = "post_id")
    var post: Post = Post(),
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User = User()
)