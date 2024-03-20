package com.yogeshseralia.blogappapis.mappers

import com.yogeshseralia.blogappapis.entities.Category
import com.yogeshseralia.blogappapis.payloads.CategoryDto

fun Category.mapToCategoryDto(): CategoryDto {
    return CategoryDto(
            this.categoryId,
            this.categoryTitle,
            this.categoryDescription
    )
}

fun CategoryDto.mapToCategory(): Category {
    return Category(
            this.categoryId,
            this.categoryTitle,
            this.categoryDescription
    )
}