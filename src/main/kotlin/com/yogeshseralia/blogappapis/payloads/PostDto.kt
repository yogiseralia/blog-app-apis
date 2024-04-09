package com.yogeshseralia.blogappapis.payloads

import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.util.*

@Getter
@Setter
@NoArgsConstructor
data class PostDto(
        var id: Int? = null,
        var title: String? = null,
        var content: String? = null,
        var imageName: String? = null,
        var addedDate: Date? = null,
        var categoryDto: CategoryDto? = null,
        var userDto: UserDto? = null
)