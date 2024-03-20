package com.yogeshseralia.blogappapis.services.impl

import com.yogeshseralia.blogappapis.entities.Category
import com.yogeshseralia.blogappapis.exceptions.ResourceNotFoundException
import com.yogeshseralia.blogappapis.mappers.mapToCategory
import com.yogeshseralia.blogappapis.mappers.mapToCategoryDto
import com.yogeshseralia.blogappapis.payloads.CategoryDto
import com.yogeshseralia.blogappapis.repositories.CategoryRepo
import com.yogeshseralia.blogappapis.services.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl : CategoryService {

    @Autowired
    lateinit var categoryRepo: CategoryRepo

    override fun createCategory(categoryDto: CategoryDto): CategoryDto {
        val category = categoryDto.mapToCategory()
        val addedCategory = categoryRepo.save(category)
        return addedCategory.mapToCategoryDto()
    }

    override fun updateCategory(categoryDto: CategoryDto, id: Int): CategoryDto {
        val category = categoryRepo.findById(id).orElseThrow { ResourceNotFoundException("Category", "Category Id", id) }
        category.categoryTitle = categoryDto.categoryTitle
        category.categoryDescription = categoryDto.categoryDescription
        val updatedCategory = categoryRepo.save(category)
        return updatedCategory.mapToCategoryDto()
    }

    override fun deleteCategory(id: Int) {
        val category = categoryRepo.findById(id).orElseThrow { ResourceNotFoundException("Category", "Category Id", id) }
        categoryRepo.delete(category)
    }

    override fun getCategoryById(id: Int): CategoryDto {
        val category = categoryRepo.findById(id).orElseThrow { ResourceNotFoundException("Category", "Category Id", id) }
        return category.mapToCategoryDto()
    }

    override fun getAllCategories(): List<CategoryDto> {
        val categories: MutableList<Category> = categoryRepo.findAll()
        val list = arrayListOf<CategoryDto>()
        categories.map {
            list.add(it.mapToCategoryDto())
        }
        return list
    }
}