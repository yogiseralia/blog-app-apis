package com.yogeshseralia.blogappapis.payloads

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@NoArgsConstructor
@Getter
@Setter
data class CategoryDto(
        val categoryId: Int,
        @field:NotBlank
        @field:Size(min = 4, max = 250)
        val categoryTitle: String,
        @field:NotBlank
        @field:Size(min = 10, max = 250)
        val categoryDescription: String
)