package com.yogeshseralia.blogappapis.payloads

import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@NoArgsConstructor
@Getter
@Setter
class PostResponse {
    var content: List<PostDto>? = null
    var pageNumber: Int = 0
    var pageSize: Int = 0
    var totalElements = 0L
    var totalPages = 0
    var lastPage = false
}