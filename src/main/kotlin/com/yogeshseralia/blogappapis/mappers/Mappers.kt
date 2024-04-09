package com.yogeshseralia.blogappapis.mappers

import com.yogeshseralia.blogappapis.entities.Category
import com.yogeshseralia.blogappapis.entities.Post
import com.yogeshseralia.blogappapis.entities.User
import com.yogeshseralia.blogappapis.payloads.CategoryDto
import com.yogeshseralia.blogappapis.payloads.PostDto
import com.yogeshseralia.blogappapis.payloads.UserDto

fun User.mapToUserDto(): UserDto {
    return UserDto(
            id = id,
            name = name,
            email = email,
            password = password,
            about = about
    )
}

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

fun Post.mapToPostDto(): PostDto {
    return PostDto(
            id = id,
            title = title,
            content = content,
            imageName = imageName,
            addedDate = addDate,
            categoryDto = category.mapToCategoryDto(),
            userDto = user.mapToUserDto()
    )
}