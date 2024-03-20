package com.yogeshseralia.blogappapis.services

import com.yogeshseralia.blogappapis.payloads.CategoryDto

interface CategoryService {

    // create
    fun createCategory(categoryDto: CategoryDto): CategoryDto

    //update
    fun updateCategory(categoryDto: CategoryDto, id: Int): CategoryDto

    //delete
    fun deleteCategory(id: Int)

    //get by id
    fun getCategoryById(id: Int): CategoryDto

    //get all
    fun getAllCategories(): List<CategoryDto>
}