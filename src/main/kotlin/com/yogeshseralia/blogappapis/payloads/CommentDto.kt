package com.yogeshseralia.blogappapis.payloads

import com.yogeshseralia.blogappapis.entities.Post
import com.yogeshseralia.blogappapis.entities.User
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.util.Date

@Getter
@Setter
@NoArgsConstructor
data class CommentDto(
    val id: Int,
    @field:NotBlank
    @field:Size(max = 1_000)
    val comment: String,
    val addDate: Date,
    val user: UserDto,
    val post: PostDto
)