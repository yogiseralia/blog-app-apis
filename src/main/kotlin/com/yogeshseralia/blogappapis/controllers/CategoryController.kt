package com.yogeshseralia.blogappapis.controllers

import com.yogeshseralia.blogappapis.payloads.ApiResponse
import com.yogeshseralia.blogappapis.payloads.CategoryDto
import com.yogeshseralia.blogappapis.services.CategoryService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/api/categories")
class CategoryController {

    @Autowired
    lateinit var categoryService: CategoryService

    //create
    @PostMapping("/")
    fun createCategory(@Valid @RequestBody categoryDto: CategoryDto): ResponseEntity<CategoryDto> {
        val createdCategory = categoryService.createCategory(categoryDto)
        return ResponseEntity(createdCategory, HttpStatus.CREATED)
    }

    //update
    @PutMapping("/{categoryId}")
    fun updateCategory(@Valid @RequestBody categoryDto: CategoryDto, @PathVariable categoryId: Int): ResponseEntity<CategoryDto> {
        val updatedCategory = categoryService.updateCategory(categoryDto, categoryId)
        return ResponseEntity.ok(updatedCategory)
    }

    //delete
    @DeleteMapping("/{categoryId}")
    fun deleteCategory(@PathVariable categoryId: Int): ResponseEntity<ApiResponse> {
        categoryService.deleteCategory(categoryId)
        val apiResponse = ApiResponse("Category deleted successfully", true)
        return ResponseEntity.ok(apiResponse)
    }

    //getbyid
    @GetMapping("/{categoryId}")
    fun getCategoryById(@PathVariable categoryId: Int): ResponseEntity<CategoryDto> {
        val categoryDto = categoryService.getCategoryById(categoryId)
        return ResponseEntity.ok(categoryDto)
    }

    //geteall
    @GetMapping("/")
    fun getCategoryById(): ResponseEntity<List<CategoryDto>> {
        val allCategories: List<CategoryDto> = categoryService.getAllCategories()
        return ResponseEntity.ok(allCategories)
    }
}