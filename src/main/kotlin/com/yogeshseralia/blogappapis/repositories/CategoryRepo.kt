package com.yogeshseralia.blogappapis.repositories

import com.yogeshseralia.blogappapis.entities.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepo : JpaRepository<Category, Int>